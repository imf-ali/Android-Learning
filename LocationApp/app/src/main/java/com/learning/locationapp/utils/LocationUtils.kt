package com.learning.locationapp.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.learning.locationapp.data.LocationData
import com.learning.locationapp.viewModel.LocationViewModel
import java.util.Locale

class LocationUtils(private val context: Context) {

  private val _fusedLocationClient: FusedLocationProviderClient =
    LocationServices.getFusedLocationProviderClient(context)

  @Suppress("MissingPermission")
  fun requestLocationUpdates(viewModel: LocationViewModel) {
    val locationCallback = object : LocationCallback() {
      override fun onLocationResult(locationResult: LocationResult) {
        super.onLocationResult(locationResult)
        locationResult.lastLocation?.let {
          viewModel.updateLocation(LocationData(it.latitude, it.longitude))
        }
      }
    }
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
    _fusedLocationClient.requestLocationUpdates(
      locationRequest,
      locationCallback,
      Looper.getMainLooper()
    )
  }

  fun hasLocationPermission(context: Context): Boolean = ContextCompat.checkSelfPermission(
    context, Manifest.permission.ACCESS_FINE_LOCATION
  ) == PackageManager.PERMISSION_GRANTED
      && ContextCompat.checkSelfPermission(
    context, Manifest.permission.ACCESS_COARSE_LOCATION
  ) == PackageManager.PERMISSION_GRANTED

  fun reverseGeocodeLocation(locationData: LocationData): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    val coordinate = LatLng(locationData.latitude, locationData.longitude)
    val address: MutableList<Address>? =
      geocoder.getFromLocation(coordinate.latitude, coordinate.longitude, 1)
    return if (address?.isNotEmpty() == true) address[0].getAddressLine(0) else "Address Not Found"
  }
}