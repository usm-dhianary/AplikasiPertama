package com.example.aplikasipertama

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2:  Migration = object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL (
            """
            CREATE TABLE attendances (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                user_id INTEGER NOT NULL,
                check_in_time INTEGER NOT NULL,
                tatitude REAL NOT NULL,
                longitude REAL NOT NULL,
                
                -- Definisi FOREIGN KEY
            )
            """.trimIndent()
        )

        //OPTION : memuat index pada user_id untuk performa query
        database.execSQL(
            "CREATE INDEX index_attendance_user_id ON attendaces(user_id)"
        )
    }
}
