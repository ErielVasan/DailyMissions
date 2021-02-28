package com.example.dailymissions

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)

var pos: Int = -1

class MissionsFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.missionsListView)
        val appContext = this.context

        listView.adapter = appContext?.let { MissionsAdapter(it) }

        //Long Click on Item
        listView.setOnItemLongClickListener { parent, view, position, id ->
            val deleteFragment = DeleteMissionFragment()
            pos = position
            deleteFragment.show(this.childFragmentManager, "deleteFragment")

            true
        }

        //Plain Click on Item
        listView.setOnItemClickListener { parent, view, position, id ->
            val editFragment = EditMissionFragment()
            pos = position
            editFragment.show(this.childFragmentManager, "editFragment")
        }
    }

    class MissionsAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context = context

        @RequiresApi(Build.VERSION_CODES.O)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val missionItem = layoutInflater.inflate(R.layout.mission_item, parent, false)
            val emptyView = View(mContext)

            val missionTitle = missionItem.findViewById<TextView>(R.id.missionTitleTextView)
            val deadline = missionItem.findViewById<TextView>(R.id.deadlineTextView)
            missionTitle.text = missionItems[position].missionName
            val deadlineDateTime =  missionItems[position].deadline

            val localDateTime = LocalDateTime.now()

            var timeColumn = ":"
            if (deadlineDateTime.minute < 10){
                timeColumn = ":0"
            }
            deadline.text = deadlineDateTime.hour.toString() + timeColumn +
                    deadlineDateTime.minute.toString() + "  " +
                    deadlineDateTime.dayOfMonth.toString() + "/" +
                    deadlineDateTime.month.value.toString() + "/" + deadlineDateTime.year.toString()

            if (!missionItems[position].isReminder) {
                missionItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }
            // If the text on the Button is Show All, the elements should be hidden, otherwise they are shown.
            if ((mContext as MissionsActivity).findViewById<Button>(R.id.showAllButton).text == "Show All"){
                if (deadlineDateTime.isAfter(localDateTime)){
                    return missionItem
                }
                else {
                    return emptyView
                }
            }else{
                return missionItem
            }

        }

        override fun getItem(position: Int): Any {
            return "nothing"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return missionItems.size
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_missions, container, false)
    }

}




