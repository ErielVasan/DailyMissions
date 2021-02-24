package com.example.dailymissions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

class AddMissionActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

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
    lateinit var missionName : String
    var isNotification = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mission)

        findViewById<Button>(R.id.addSetReminderButton).setOnClickListener {
            getDateTimeCalendar()

            DatePickerDialog(this, this, year, month, day).show()
        }

        findViewById<Button>(R.id.addBackButton).setOnClickListener {
            val intent = Intent(this, MissionsActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.addButton).setOnClickListener {

            missionName = findViewById<TextView>(R.id.addMissionNameTextField).text.toString()
            isNotification = findViewById<CheckBox>(R.id.addNotificationCheckBox).isChecked

            val addedMission = MissionItem(isNotification, localDateTime, missionName)
            missionItems.add(addedMission)
            val intent = Intent(this, MissionsActivity::class.java)
            missionItems.sortBy { it.deadline }
            startActivity(intent)
            finish()

        }

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
        TimePickerDialog(this, this, hour, minute, is24hour).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute

        findViewById<TextView>(R.id.addDeadlineTextView).setText("$savedHour:$savedMinute  $savedDay/$savedMonth/$savedYear")
        localDateTime = LocalDateTime.of(savedYear, savedMonth, savedDay, savedHour, savedMinute)

    }

    override fun onBackPressed() {
        val intent = Intent(this, MissionsActivity::class.java)
        startActivity(intent)
        finish()
    }
}