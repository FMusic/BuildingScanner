package org.bs.pr.bl

import android.content.Context
import org.bs.pr.bl.scanners.GpsScanner
import org.bs.pr.bl.scanners.TransmitterScanner
import org.bs.pr.bl.scanners.WifiScanner
import org.bs.pr.interfaces.PlaceReaderListener
import org.bs.pr.model.*
import org.bs.pr.model.sensors.GpsSpot
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.sensors.WifiAvailable

class DataCollector(ctx: Context, prl: PlaceReaderListener, var building: Building): PlaceReaderListener {
    val listeners = listOf(prl, this)

    var scanners = listOf(
        GpsScanner(ctx),
        TransmitterScanner(ctx),
        WifiScanner(ctx)
    )

    lateinit var room: Room
    var passage = Passage()
    lateinit var space: Space

    lateinit var spot: Spot

    fun stopScan() {
        scanners.forEach { it.stopScan() }
    }

    fun newRoom(name: String?) = if (name == null) {
        space = passage
        scanners.forEach{
            for (listener in listeners) {
                it.addListener(listener)
            }
            it.scan()
        }
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