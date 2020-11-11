package org.bs.pr.interfaces

import org.bs.pr.model.sensors.GpsSpot
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.Room
import org.bs.pr.model.sensors.WifiAvailable

interface PlaceReaderListener {
    fun onRoomScanned(r: Room)
    fun onRoomStarted(r: Room)
    fun onGpsChange(gpsSpot: GpsSpot)
    fun onMobileSpotChange(mobileSpot: MobileSpot)
    fun onNewWifiDetected(wifiAvailable: WifiAvailable)
}