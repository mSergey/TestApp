package com.gmail.zajcevserg.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [FakerDataEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val fakerDataDao: FakerDataDao
}