package com.example.dailymissions

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class MissionItem(_isReminder: Boolean, _deadline: LocalDateTime, _missionName: String){
    var isReminder: Boolean = _isReminder
    var deadline: LocalDateTime = _deadline
    var missionName: String = _missionName
    var iscompleted: Boolean = false

    override fun toString(): String {
        var stringedObject = ""
        stringedObject = "|n$missionName|d$deadline|r$isReminder||"
        return stringedObject
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun unpack(stringedObject: String){

        fun removeZeros(timeString: String): String {

            if (timeString.substring(0, 1) == "0"){
                return timeString.substring(1)
            }else{
                return timeString
            }

        }

        missionName = stringedObject.substringAfter("|n").substringBefore("|d")
        isReminder = stringedObject.substringAfter("|r").substringBefore("||").toBoolean()
        val dateString = stringedObject.substringAfter("|d").substringBefore("|r")

        val year = dateString.substring(0, 4).toInt()
        Log.d("STRING year", year.toString())

        var stringMonth = dateString.substring(5, 7)
        Log.d("STRING MONTH", stringMonth)
        stringMonth = removeZeros(stringMonth)
        Log.d("STRING MONTH AFTER", stringMonth)
        val month = stringMonth.toInt()

        var stringDay = dateString.substring(8, 10)
        stringDay = removeZeros(stringDay)
        val day = stringDay.toInt()

        var stringHour = dateString.substring(11, 13)
        stringHour = removeZeros(stringHour)
        val hour = stringHour.toInt()

        var stringMinute = dateString.substring(14, 16)
        stringMinute = removeZeros(stringMinute)
        val minute = stringMinute.toInt()

        val localDateTime = LocalDateTime.of(year, month, day, hour, minute)
        deadline = localDateTime

    }

}