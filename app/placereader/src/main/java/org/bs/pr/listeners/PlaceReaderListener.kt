package org.bs.pr.listeners

import org.bs.pr.model.GpsSpot
import org.bs.pr.model.MobileSpot
import org.bs.pr.model.Room
import org.bs.pr.model.WifiAvailable

interface PlaceReaderListener {
    fun onRoomScanned(r: Room)
    fun onRoomStarted(r: Room)
    fun onGpsChange(gpsSpot: GpsSpot)
    fun onMobileSpotChange(mobileSpot: MobileSpot)
    fun onNewWifiDetected(wifiAvailable: WifiAvailable)
}