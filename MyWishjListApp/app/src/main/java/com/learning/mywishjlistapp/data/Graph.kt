package com.learning.mywishjlistapp.data

import android.content.Context
import androidx.room.Room

object Graph {
  private lateinit var dataBase: WishDatabase

  val wishRepository by lazy {
    WishRepository(wishDao = dataBase.wishDao())
  }

  fun provide(context: Context){
    dataBase = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
  }
}