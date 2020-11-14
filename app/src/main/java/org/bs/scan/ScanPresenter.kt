package org.bs.scan

import org.bs.pr.PlaceReader
import org.bs.pr.interfaces.PlaceReaderListener
import org.bs.pr.model.sensors.GpsSpot
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.Room
import org.bs.pr.model.sensors.WifiAvailable
import org.bs.views.DialogHelper

class ScanPresenter(private var sa: ScanActivity) : PlaceReaderListener {
    var placeReader = PlaceReader(this.sa, this, this.sa.buildingName)


    override fun onRoomScanned(r: Room) {
        var text = this.sa.tvLog.text.toString()
        text += r.roomName + "finished"
        this.sa.tvLog.text = text
    }

    override fun onRoomStarted(r: Room) {
        var text = this.sa.tvLog.text.toString()
        text += r.roomName + "started"
        this.sa.tvRoomName.text = r.roomName
        this.sa.tvLog.text = text
    }

    override fun onGpsChange(gpsSpot: GpsSpot) {
        var text = this.sa.tvLog.text.toString()
        text += "New gps: " + gpsSpot.altitude + " " + gpsSpot.latitude + " " + gpsSpot.longitude
        this.sa.tvLog.text = text
    }

    override fun onMobileSpotChange(mobileSpot: MobileSpot) {
        var text = this.sa.tvLog.text.toString()
        text += "Loaded new mobile spot"
        this.sa.tvLog.text = text
    }

    override fun onNewWifiDetected(wifiAvailable: List<WifiAvailable>) {
        var text = this.sa.tvLog.text.toString()
        text += "new list of wifis detected"
        this.sa.tvLog.text = text
    }

    fun startScan() {
        placeReader.startScan()
    }

    fun stopScan() {
        placeReader.stopScan()
    }

    fun newRoom() {
        DialogHelper.showDialog(this.sa, "New room title:", placeReader::newRoom)
    }
}