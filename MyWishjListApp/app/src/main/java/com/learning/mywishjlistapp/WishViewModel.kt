package com.learning.mywishjlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.mywishjlistapp.data.Graph
import com.learning.mywishjlistapp.data.WishItem
import com.learning.mywishjlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val wishRepository: WishRepository = Graph.wishRepository): ViewModel() {
  var wishTitleState by mutableStateOf("")
  var wishDescriptionState by mutableStateOf("")

  fun onWishTitleChange(newString: String){
    wishTitleState = newString
  }

  fun onWishDescriptionChange(newString: String){
    wishDescriptionState = newString
  }

  lateinit var getAllWishes: Flow<List<WishItem>>

  init {
    viewModelScope.launch {
      getAllWishes = wishRepository.getWish()
    }
  }

  fun addWish(wishItem: WishItem) {
    viewModelScope.launch(Dispatchers.IO) {
      wishRepository.addWish(wishItem)
    }
  }

  fun getWishById(id: Long): Flow<WishItem>{
    return wishRepository.getWishById(id)
  }

  fun updateWish(wishItem: WishItem) {
    viewModelScope.launch(Dispatchers.IO) {
      wishRepository.updateWish(wishItem)
    }
  }

  fun deleteWish(wishItem: WishItem) {
    viewModelScope.launch(Dispatchers.IO) {
      wishRepository.deleteWish(wishItem)
    }
  }
}