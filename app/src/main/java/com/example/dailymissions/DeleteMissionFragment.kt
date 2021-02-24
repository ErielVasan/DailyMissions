package com.example.dailymissions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.DialogFragment

open class DeleteMissionFragment : DialogFragment()  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootview: View = inflater.inflate(R.layout.fragment_delete_mission, container, false)

        rootview.findViewById<Button>(R.id.deleteBackButton).setOnClickListener {
            dismiss()
        }

        rootview.findViewById<Button>(R.id.abandonButton).setOnClickListener {
            missionItems.removeAt(pos)
            var listView = this.activity?.findViewById<ListView>(R.id.missionsListView)
            (listView?.adapter as MissionsFragment.MissionsAdapter).notifyDataSetChanged()
            dismiss()

        }

        return rootview
    }

}