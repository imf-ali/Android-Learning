package com.learning.mywishjlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class WishItem(
  @PrimaryKey(autoGenerate = true)
  val id: Long = 0L,
  @ColumnInfo(name = "wish-title")
  val title: String = "",
  @ColumnInfo(name = "wish-desc")
  val description: String = ""
)

object DummyWishList {
  val wishList = listOf(
    WishItem(title = "Learn Android", description = "Learn Android in Kotlin and Java"),
    WishItem(
      title = "Icon and Splash screen",
      description = "Learn to make Icon and Splash screen"
    ),
    WishItem(title = "Deploy App", description = "Deploy the app on Google Play Store")
  )
}