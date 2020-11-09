package org.bs.pr.bl.collector

import android.content.Context
import org.bs.pr.bl.gps.GpsScanner
import org.bs.pr.bl.mobile.TransmitterScanner
import org.bs.pr.bl.wifi.WifiScanner
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.*
import org.bs.pr.model.sensors.GpsSpot
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.sensors.WifiAvailable

class DataCollector(ctx: Context, prl: PlaceReaderListener, var building: Building): PlaceReaderListener {
    val listeners = listOf(prl, this)

    var scanners = listOf(
        GpsScanner(ctx, listeners),
        TransmitterScanner(ctx, listeners),
        WifiScanner(ctx, listeners)
    )

    lateinit var room: Room
    var passage = Passage()
    lateinit var space: Space

    lateinit var spot: Spot

    fun stopScan() {
        scanners.forEach { x -> x.stopScan() }
    }

    fun newRoom(name: String?) = if (name == null) {
        space = passage
        scanners.forEach { x -> x.scan() }
    } else {
        room = Room(name)
        space = room
    }

    //<editor-fold desc="DontUseThis">
    override fun onRoomScanned(r: Room) {
        throw IllegalAccessError("Should not access this function")
    }

    override fun onRoomStarted(r: Room) {
        throw IllegalAccessError("Should not access this function")
    }
    //</editor-fold>

    override fun onGpsChange(gpsSpot: GpsSpot) {
        checkSpot()
        if (spot.isGpsInitialized()){
            space.spots.add(spot)
            spot = Spot()
        }
        spot.gpsSpot = gpsSpot
    }

    override fun onMobileSpotChange(mobileSpot: MobileSpot) {
        checkSpot()
        if (spot.isMobileInitialized()){
            space.spots.add(spot)
            spot = Spot()
        }
        spot.mobileSpot = mobileSpot
    }

    override fun onNewWifiDetected(wifiAvailable: WifiAvailable) {
        checkSpot()
        if (spot.isGpsInitialized()){
            space.spots.add(spot)
            spot = Spot()
        }
        spot.wifiAvailable = wifiAvailable
    }

    private fun checkSpot() {
        if (!::spot.isInitialized){
            spot = Spot()
        }
    }


}