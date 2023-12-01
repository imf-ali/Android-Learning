package com.learning.mywishjlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.learning.mywishjlistapp.data.WishItem
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
  id: Long,
  viewModel: WishViewModel,
  navController: NavController
) {
  val snackMessage = remember {
    mutableStateOf("")
  }
  val scope = rememberCoroutineScope()
  val scaffoldState = rememberScaffoldState()
  if(id != 0L){
    val wishItem = viewModel.getWishById(id).collectAsState(initial = WishItem(0L, "", ""))
    viewModel.wishTitleState = wishItem.value.title
    viewModel.wishDescriptionState = wishItem.value.description
  } else {
    viewModel.wishTitleState = ""
    viewModel.wishDescriptionState = ""
  }
  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      AppBarView(
        title = if (id != 0L) "Update Wish" else "Add Wish",
        onBackNavClicked = { navController.navigateUp() })
    }
  ) {
    Column(
      modifier = Modifier
        .padding(it)
        .wrapContentSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Spacer(modifier = Modifier.height(10.dp))
      WishTextField(
        label = "Title",
        value = viewModel.wishTitleState,
        onValueChange = { title ->
          viewModel.onWishTitleChange(title)
        }
      )
      Spacer(modifier = Modifier.height(10.dp))
      WishTextField(
        label = "Description",
        value = viewModel.wishDescriptionState,
        onValueChange = { title ->
          viewModel.onWishDescriptionChange(title)
        }
      )
      Spacer(modifier = Modifier.height(10.dp))
      Button(onClick = {
        if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
          if(id != 0L){
            viewModel.updateWish(
              WishItem(
                id = id,
                title = viewModel.wishTitleState.trim(),
                description = viewModel.wishDescriptionState.trim()
              )
            )
          } else {
            viewModel.addWish(
              WishItem(
                title = viewModel.wishTitleState.trim(),
                description = viewModel.wishDescriptionState.trim()
              )
            )
            snackMessage.value = "Wish item has been created"
          }
        } else {
          snackMessage.value = "Enter fields to create a wish"
        }
        scope.launch {
          scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
          navController.navigateUp()
        }
      }) {
        Text(
          text = if (id != 0L) "Update Wish" else "Add Wish",
          style = TextStyle(fontSize = 18.sp)
        )
      }
    }
  }
}

@Composable
fun WishTextField(
  label: String,
  value: String,
  onValueChange: (String) -> Unit
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    label = { Text(text = label, color = Color.Black) },
    modifier = Modifier.fillMaxWidth(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    colors = TextFieldDefaults.outlinedTextFieldColors(
      textColor = Color.Black,
      focusedBorderColor = colorResource(id = R.color.black),
      unfocusedBorderColor = colorResource(id = R.color.black),
      cursorColor = colorResource(id = R.color.black),
      focusedLabelColor = colorResource(id = R.color.black),
      unfocusedLabelColor = colorResource(id = R.color.black),
    )
  )
}