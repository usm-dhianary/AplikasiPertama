package com.example.aplikasipertama

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserEntity::class
    ], version = 1
)
abstract class AbsensiDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        private var INSTANCE: AbsensiDatabase? = null

        fun getDatabase(context: Context): AbsensiDatabase{
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AbsensiDatabase::class.java,
                    "aplikasiabsensi"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}