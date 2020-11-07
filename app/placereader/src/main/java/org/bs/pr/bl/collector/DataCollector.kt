package org.bs.pr.bl.collector

import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.Room
import org.bs.pr.model.sensors.GpsSpot
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.sensors.WifiAvailable

class DataCollector: PlaceReaderListener {
    override fun onRoomScanned(r: Room) {
        TODO("Not yet implemented")
    }

    override fun onRoomStarted(r: Room) {
        TODO("Not yet implemented")
    }

    override fun onGpsChange(gpsSpot: GpsSpot) {
        TODO("Not yet implemented")
    }

    override fun onMobileSpotChange(mobileSpot: MobileSpot) {
        TODO("Not yet implemented")
    }

    override fun onNewWifiDetected(wifiAvailable: WifiAvailable) {
        TODO("Not yet implemented")
    }
}