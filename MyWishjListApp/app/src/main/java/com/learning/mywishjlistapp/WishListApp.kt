package com.learning.mywishjlistapp

import android.app.Application
import com.learning.mywishjlistapp.data.Graph

class WishListApp: Application() {
  override fun onCreate() {
    super.onCreate()
    Graph.provide(this)
  }
}