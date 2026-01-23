package com.example.myapplication2

import android.app.Application
import androidx.room.Room
import com.example.myapplication2.data.database.AppDatabase
import com.example.myapplication2.di.AppComponent
import com.example.myapplication2.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var appComponent: AppComponent
        private set
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()

        // Build Room database with destructive migration for development
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_database"
        )
            .fallbackToDestructiveMigration() // this will wipe DB if schema changed
            .build()
    }


}