package com.learning.mywishjlistapp.data

data class WishItem(
  val id: Long = 0L,
  val title: String = "",
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