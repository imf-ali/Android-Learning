package com.learning.mywishjlistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learning.mywishjlistapp.data.DummyWishList
import com.learning.mywishjlistapp.data.WishItem

@Composable
fun HomeView(viewModel: WishViewModel, navController: NavController){
  Scaffold(
    topBar = { AppBarView(title = "WishList") },
    floatingActionButton = {
      FloatingActionButton(
        modifier = Modifier.padding(all = 4.dp),
        contentColor = Color.White,
        backgroundColor = Color.Black,
        onClick = { navController.navigate(Screen.AddScreen.route) }
      ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Wish list")
      }
    }
  ) {
    LazyColumn(modifier = Modifier
      .fillMaxSize()
      .padding(it)){
      items(DummyWishList.wishList){
        item -> WishListItem(wishItem = item) {

        }
      }
    }
  }
}

@Composable
fun WishListItem(wishItem: WishItem, onClick: () -> Unit){
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 8.dp, start = 8.dp, end = 8.dp)
      .clickable { onClick() },
    elevation = 10.dp,
    backgroundColor = Color.White
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = wishItem.title, fontWeight = FontWeight.Bold)
      Text(text = wishItem.description)
    }
  }
}