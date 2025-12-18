package com.example.aplikasipertama

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserEntity::class,
        AttendanceEntity::class
    ], version = 2,
    exportSchema = false
)
abstract class AbsensiDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun attendaceDao(): AttendanceDao
    companion object {
        private var INSTANCE: AbsensiDatabase? = null

        fun getDatabase(context: Context): AbsensiDatabase{
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AbsensiDatabase::class.java,
                    "aplikasiabsensi"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                instance
            }
        }
    }
}