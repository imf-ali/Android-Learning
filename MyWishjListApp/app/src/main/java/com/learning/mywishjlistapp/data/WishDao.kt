package com.learning.mywishjlistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  abstract suspend fun addWish(wishItem: WishItem)

  @Update
  abstract suspend fun updateWish(wishItem: WishItem)

  @Query("Select * from `wish-table`")
  abstract fun getAllWishes() : Flow<List<WishItem>>

  @Delete
  abstract suspend fun deleteWish(wishItem: WishItem)

  @Query("Select * from `wish-table` where id=:id")
  abstract fun getWishById(id: Long) : Flow<WishItem>
}