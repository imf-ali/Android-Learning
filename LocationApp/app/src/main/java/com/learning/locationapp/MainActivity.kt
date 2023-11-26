package com.learning.locationapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learning.locationapp.ui.theme.LocationAppTheme
import com.learning.locationapp.utils.LocationUtils
import com.learning.locationapp.viewModel.LocationViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val viewModel: LocationViewModel = viewModel()
      LocationAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          MyApp(viewModel)
        }
      }
    }
  }
}

@Composable
fun MyApp(viewModel: LocationViewModel){
  val context = LocalContext.current
  val locationUtils = LocationUtils(context)
  LocationDisplay(locationUtils = locationUtils, viewModel, context = context)
}

@Composable
fun LocationDisplay(locationUtils: LocationUtils, viewModel: LocationViewModel, context: Context) {

  val location = viewModel.location.value
  val address = location?.let {
    locationUtils.reverseGeocodeLocation(location)
  }

  val requestPermissionLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestMultiplePermissions(),
    onResult = { permissions ->
      if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
      ) {
        locationUtils.requestLocationUpdates(viewModel)
      } else {
        val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
          context as MainActivity,
          Manifest.permission.ACCESS_FINE_LOCATION
        ) || ActivityCompat.shouldShowRequestPermissionRationale(
          context,
          Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (rationaleRequired) {
          Toast.makeText(context, "Location is required for this feature", Toast.LENGTH_LONG).show()
        } else {
          Toast.makeText(context, "Location is required. Enable in settings", Toast.LENGTH_LONG)
            .show()
        }
      }
    }
  )

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    if(location != null) {
      Text("Address is: ${location.latitude} ${location.longitude}")
      if (address != null) {
        Text(address)
      }
    } else {
      Text("Location not found")
    }
    Button(onClick = {
      if (locationUtils.hasLocationPermission(context)) {
        locationUtils.requestLocationUpdates(viewModel)
      } else {
        requestPermissionLauncher.launch(
          arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
          )
        )
      }
    }) {
      Text(text = "Get location")
    }
  }
}