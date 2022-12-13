package com.example.data.cache

import android.content.Context
import com.example.data.ItemDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(ItemDatabase.Schema, context, "item.db")
    }
}