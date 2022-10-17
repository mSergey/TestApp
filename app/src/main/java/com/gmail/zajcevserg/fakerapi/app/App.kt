package com.gmail.zajcevserg.fakerapi.app

import android.app.Application
import com.gmail.zajcevserg.fakerapi.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(
                listOf(
                    viewModelsModule,
                    dataBaseInstanceModule,
                    repositoryModule,
                    daoModule,
                    serviceModule,
                    useCaseModule
                )
            )
        }
    }
}