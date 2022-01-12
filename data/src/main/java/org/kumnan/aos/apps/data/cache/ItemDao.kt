package org.kumnan.aos.apps.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.kumnan.aos.apps.data.entity.ItemEntity

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun getItemList(): Flow<List<ItemEntity>>

    @Insert
    suspend fun insertItem(vararg item: ItemEntity)

    @Insert
    suspend fun insertItem(items: List<ItemEntity>)
}