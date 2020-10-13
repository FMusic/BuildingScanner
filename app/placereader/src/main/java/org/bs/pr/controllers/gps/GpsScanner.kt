package org.bs.pr.controllers.gps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import org.bs.pr.controllers.Scanner
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.GpsSpot
import org.bs.pr.model.Room
import java.lang.Exception

class GpsScanner(context: Context, listener: PlaceReaderListener) : Scanner {
    private lateinit var room: Room
    private var shouldScan = false
    var ctx: Context = context
    var readerListener = listener

    init {
        shouldScan = true
    }

    override fun changeRoom(newRoom: Room) {
        room = newRoom
    }

    override fun scan() {
        if (shouldScan) {
            var mFusedLocationClient = LocationServices.getFusedLocationProviderClient(ctx)
            var mSettingsClient = LocationServices.getSettingsClient(ctx)

            if (ActivityCompat.checkSelfPermission(ctx,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ctx,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                throw Exception("Don't have permissions")
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            } else{
                while (shouldScan) {
                }
                mFusedLocationClient.lastLocation.addOnSuccessListener {
                    process(it.altitude, it.latitude, it.longitude)
                }
            }
        }
    }

    private fun process(altitude: Double, latitude: Double, longitude: Double) {
        val gpsSpot = GpsSpot(altitude, latitude, longitude)
        room.gpsSpots.add(gpsSpot)
        readerListener.onGpsChange(gpsSpot)
    }

    override fun stopScan() {
        shouldScan = false
    }

}