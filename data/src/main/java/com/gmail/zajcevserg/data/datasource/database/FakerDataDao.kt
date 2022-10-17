package com.gmail.zajcevserg.data.datasource.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE


@Dao
abstract class FakerDataDao {


    @Transaction
    open suspend fun updateLastSaved(data: List<FakerDataEntity>) {
        deleteAll()
        saveData(data = data)
    }

    @Query("SELECT * FROM last_saved_faker_data_table")
    abstract suspend fun getData(): List<FakerDataEntity>

    @Query("DELETE FROM last_saved_faker_data_table")
    abstract suspend fun deleteAll()

    @Insert(onConflict = REPLACE)
    abstract suspend fun saveData(data: List<FakerDataEntity>)
}