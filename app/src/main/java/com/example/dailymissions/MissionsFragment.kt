package com.example.dailymissions

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MissionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MissionsFragment : Fragment() {

    companion object {
        fun newInstance() = MissionsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listview = view.findViewById<ListView>(R.id.missionsListView)
        val appContext = this.activity
        listview.adapter = appContext?.let { MissionsAdapter(it) }
    }

    private class MissionsAdapter(context: Context): BaseAdapter() {

        private val mContext: Context

        init{
            mContext = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val missionItem = layoutInflater.inflate(R.layout.mission_item, parent, false)
            return missionItem

        }

        override fun getItem(position: Int): Any {
            return "nothing"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return 7
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
