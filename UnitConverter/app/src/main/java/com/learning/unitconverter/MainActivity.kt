package com.learning.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      UnitConverterTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          UnitConverter()
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {

  var inputValue by remember { mutableStateOf("") }
  var outputValue by remember { mutableStateOf("") }
  var inputUnit by remember { mutableStateOf("Centimeters") }
  var outputUnit by remember { mutableStateOf("Meters") }
  var inExpanded by remember { mutableStateOf(false) }
  var outExpanded by remember { mutableStateOf(false) }
  var conversionFactor by remember { mutableStateOf(0.01) }

  fun convertUnits() {
    val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0;
    outputValue = (((inputValueDouble * conversionFactor * 100.0).roundToInt() / 100.0).toString())
  }

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text = "Unit Converter")
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
      value = inputValue,
      label = { Text(text = "Enter the value") },
      onValueChange = { inputValue = it }
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row {
      Box {
        Button(onClick = { inExpanded = true }) {
          Text(text = "Select")
          Icon(Icons.Default.ArrowDropDown, contentDescription = "")
        }
        DropdownMenu(expanded = inExpanded, onDismissRequest = { inExpanded = false }) {
          DropdownMenuItem(
            text = { Text(text = "Centimeters") },
            onClick = {
              inExpanded = false
              inputUnit = "Centimeters"
              conversionFactor = 0.01
              convertUnits()
            }
          )
          DropdownMenuItem(
            text = { Text(text = "Meters") },
            onClick = { /*TODO*/ }
          )
          DropdownMenuItem(
            text = { Text(text = "Feet") },
            onClick = { /*TODO*/ }
          )
          DropdownMenuItem(
            text = { Text(text = "Millimeters") },
            onClick = { /*TODO*/ }
          )
        }
      }
      Spacer(modifier = Modifier.width(16.dp))
      Box {
        Button(onClick = { outExpanded = true }) {
          Text(text = "Select")
          Icon(Icons.Default.ArrowDropDown, contentDescription = "")
        }
        DropdownMenu(expanded = outExpanded, onDismissRequest = { outExpanded = false }) {
          DropdownMenuItem(
            text = { Text(text = "Centimeters") },
            onClick = { /*TODO*/ }
          )
          DropdownMenuItem(
            text = { Text(text = "Meters") },
            onClick = { /*TODO*/ }
          )
          DropdownMenuItem(
            text = { Text(text = "Feet") },
            onClick = { /*TODO*/ }
          )
          DropdownMenuItem(
            text = { Text(text = "Millimeters") },
            onClick = { /*TODO*/ }
          )
        }
      }
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(text = "Result:")
  }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
  UnitConverter()
}