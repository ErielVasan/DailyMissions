package com.example.dailymissions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.time.LocalDateTime
import java.util.*

class EditMissionFragment : DialogFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    var is24hour = true
    lateinit var localDateTime: LocalDateTime

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootview: View = inflater.inflate(R.layout.fragment_edit_mission, container, false)


        rootview.findViewById<EditText>(R.id.editMissionNameTextField).setText(missionItems[pos].missionName)
        val deadlineDateTime = missionItems[pos].deadline
        localDateTime = deadlineDateTime

        var timeColumn = ":"
        if (deadlineDateTime.minute < 10){
            timeColumn = ":0"
        }
        rootview.findViewById<TextView>(R.id.editDeadlineTextView).setText(
            deadlineDateTime.hour.toString() + timeColumn + deadlineDateTime.minute.toString() + "  " +
            deadlineDateTime.dayOfMonth.toString() + "/" + deadlineDateTime.month.value.toString() +
                    "/" + deadlineDateTime.year.toString()
        )


        rootview.findViewById<CheckBox>(R.id.editNotificationCheckBox).isChecked = missionItems[pos].isReminder

        //back button
        rootview.findViewById<Button>(R.id.editBackButton).setOnClickListener {
            dismiss()
        }

        //submit Edit button
        rootview.findViewById<Button>(R.id.editButton).setOnClickListener {
            missionItems[pos].deadline = localDateTime
            missionItems[pos].missionName = rootview.findViewById<EditText>(R.id.editMissionNameTextField).text.toString()
            missionItems[pos].isReminder = rootview.findViewById<CheckBox>(R.id.editNotificationCheckBox).isChecked

            db.collection("reminders").document(missionItems[pos].id).update("reminder", missionItems[pos].toString())

            missionItems.sortBy { it.deadline }
            val listView = this.activity?.findViewById<ListView>(R.id.missionsListView)
            (listView?.adapter as MissionsFragment.MissionsAdapter).notifyDataSetChanged()
            dismiss()

        }

        //DateTimePicker button
        rootview.findViewById<Button>(R.id.editSetReminderButton).setOnClickListener {
            getDateTimeCalendar()

            this.context?.let { it1 -> DatePickerDialog(it1, this, year, month, day).show() }
        }

        return rootview
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year= cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month + 1
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(this.activity, this, hour, minute, is24hour).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        var timeColumn = ":"
        if (savedMinute < 10){
            timeColumn = ":0"
        }
        this.view?.findViewById<TextView>(R.id.editDeadlineTextView)?.setText("$savedHour$timeColumn$savedMinute $savedDay/$savedMonth/$savedYear")
        localDateTime = LocalDateTime.of(savedYear, savedMonth, savedDay, savedHour, savedMinute)

    }
}