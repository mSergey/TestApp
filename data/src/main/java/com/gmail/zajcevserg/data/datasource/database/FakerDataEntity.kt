package com.gmail.zajcevserg.data.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "last_saved_faker_data_table")
data class FakerDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val url: String,
)

