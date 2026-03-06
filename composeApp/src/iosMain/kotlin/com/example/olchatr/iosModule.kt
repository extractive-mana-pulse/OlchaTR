package com.example.olchatr

import com.example.olchatr.core.database.AppDatabase
import org.koin.dsl.module

val iosModule = module {
    single<AppDatabase> { provideDatabase() }
    single { get<AppDatabase>().productDao() }
}