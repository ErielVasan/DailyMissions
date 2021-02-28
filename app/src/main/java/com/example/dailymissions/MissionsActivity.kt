package com.example.dailymissions

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime

var missionItems = arrayListOf<MissionItem>()

val db = FirebaseFirestore.getInstance()
class MissionsActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_missions)

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseUser = firebaseAuth.currentUser?.email.toString()

        missionItems.clear()
        db.collection("reminders").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    if (document.data["user"].toString() == firebaseUser){
                        val missionItem = MissionItem(false, LocalDateTime.now(), "")
                        missionItem.unpack(document.data["reminder"].toString())
                        missionItem.id = document.id
                        missionItems.add(missionItem)
                        missionItems.sortBy { it.deadline }
                    }

                    Log.d("TAG", document.id + " => " + document.data["user"])
                }
                val listView = this?.findViewById<ListView>(R.id.missionsListView)
                (listView?.adapter as MissionsFragment.MissionsAdapter).notifyDataSetChanged()
            }
        }


        val accountNameView = findViewById<TextView>(R.id.accountName)
        accountNameView.text = firebaseUser


        findViewById<Button>(R.id.signOutButton).setOnClickListener {
            missionItems.clear()
            firebaseAuth.signOut()
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<FloatingActionButton>(R.id.addMissionFloatingActionButton).setOnClickListener {
            val intent = Intent(this, AddMissionActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.showAllButton).setOnClickListener {
            when(findViewById<Button>(R.id.showAllButton).text){
                "Show All" -> findViewById<Button>(R.id.showAllButton).text = "Hide"
                "Hide" -> findViewById<Button>(R.id.showAllButton).text = "Show All"
            }
            val listView = findViewById<ListView>(R.id.missionsListView)
            (listView?.adapter as MissionsFragment.MissionsAdapter).notifyDataSetChanged()
        }
    }
    override fun onBackPressed() {
        val intent = Intent()
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }
}