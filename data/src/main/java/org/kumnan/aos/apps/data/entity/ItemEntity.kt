package org.kumnan.aos.apps.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items")
data class ItemEntity(
    val title: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)