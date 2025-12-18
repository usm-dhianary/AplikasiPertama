package com.example.aplikasipertama

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AttendanceDao {
        @Insert
    suspend fun insertAttendance(attendace: AttendanceEntity): Long
}