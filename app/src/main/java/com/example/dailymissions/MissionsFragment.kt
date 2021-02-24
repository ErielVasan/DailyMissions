package com.example.dailymissions

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
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
var missionItems = arrayListOf<MissionItem>()
var pos: Int = -1

class MissionsFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<ListView>(R.id.missionsListView)
            val appContext = this.activity

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
            Toast.makeText(appContext, missionItems[position].missionName, Toast.LENGTH_SHORT).show()
        }
    }

    class MissionsAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context = context


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val missionItem = layoutInflater.inflate(R.layout.mission_item, parent, false)

            var missionTitle = missionItem.findViewById<TextView>(R.id.missionTitleTextView)
            var deadline = missionItem.findViewById<TextView>(R.id.deadlineTextView)
            missionTitle.text = missionItems[position].missionName
            deadline.text = missionItems[position].deadline.toString()

            if (!missionItems[position].isReminder) {
                missionItem.setBackgroundColor(Color.parseColor("#FFFFFF"))
            }

            return missionItem

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

    override fun onResume() {
        super.onResume()

    }

}




