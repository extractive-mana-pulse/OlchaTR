package com.example.olchatr

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.example.olchatr.core.data_store.createDataStore
import com.example.olchatr.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    single<DataStore<Preferences>> {
        createDataStore(
            producePath = {
                androidContext()
                    .filesDir
                    .resolve("settings.preferences_pb")
                    .absolutePath
            }
        )
    }

    single { get<AppDatabase>().productDao() }
}