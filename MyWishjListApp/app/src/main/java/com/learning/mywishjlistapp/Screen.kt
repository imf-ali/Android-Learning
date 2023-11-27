package com.learning.mywishjlistapp

sealed class Screen(val route: String){
  object HomeScreen: Screen("home")
  object AddScreen: Screen("add_screen")
}
