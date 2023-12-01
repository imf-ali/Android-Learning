package com.learning.mywishjlistapp.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {
  suspend fun addWish(wishItem: WishItem){
    wishDao.addWish(wishItem)
  }

  fun getWish() : Flow<List<WishItem>> = wishDao.getAllWishes()

  fun getWishById(id: Long) : Flow<WishItem> = wishDao.getWishById(id)

  suspend fun updateWish(wishItem: WishItem) {
    wishDao.updateWish(wishItem)
  }

  suspend fun deleteWish(wishItem: WishItem){
    wishDao.deleteWish(wishItem)
  }
}