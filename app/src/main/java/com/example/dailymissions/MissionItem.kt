package com.example.dailymissions

import java.time.LocalDateTime

class MissionItem(_isReminder: Boolean, _deadline: LocalDateTime, _missionName: String){
    var isReminder: Boolean = _isReminder
    var deadline: LocalDateTime = _deadline
    var missionName: String = _missionName
    var iscompleted: Boolean = false

    fun toggleReminder(){
        isReminder = !isReminder

    }


}