package com.example.olchatr

import androidx.room.Room
import com.example.olchatr.core.database.AppDatabase
import platform.Foundation.NSHomeDirectory

fun provideDatabase(): AppDatabase {

    val dbPath = NSHomeDirectory() + "/app.db"

    return Room.databaseBuilder<AppDatabase>(
        name = dbPath
    )
        .setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())
        .build()
}