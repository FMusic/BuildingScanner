package org.bs.scan

import org.bs.pr.PlaceReader
import org.bs.pr.interfaces.PlaceReaderListener
import org.bs.pr.model.sensors.GpsSpot
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.Room
import org.bs.pr.model.sensors.WifiAvailable
import org.bs.views.DialogHelper

class ScanPresenter( var scanActivity: ScanActivity) : PlaceReaderListener {
    var placeReader = PlaceReader(scanActivity, this, scanActivity.buildingName)
    var ctx: ScanActivity = scanActivity


    override fun onRoomScanned(r: Room) {
        var text = ctx.tvLog.text.toString()
        text += r.roomName + "finished"
        ctx.tvLog.text = text
    }

    override fun onRoomStarted(r: Room) {
        var text = ctx.tvLog.text.toString()
        text += r.roomName + "started"
        scanActivity.tvRoomName.text = r.roomName
        ctx.tvLog.text = text
    }

    override fun onGpsChange(gpsSpot: GpsSpot) {
        var text = ctx.tvLog.text.toString()
        text += "New gps: " + gpsSpot.altitude + " " + gpsSpot.latitude + " " + gpsSpot.longitude
        ctx.tvLog.text = text
    }

    override fun onMobileSpotChange(mobileSpot: MobileSpot) {
        var text = ctx.tvLog.text.toString()
        text += "Loaded new mobile spot"
        ctx.tvLog.text = text
    }

    override fun onNewWifiDetected(wifiAvailable: WifiAvailable) {
        var text = ctx.tvLog.text.toString()
        text += "new wifi detected ${wifiAvailable.SSID}"
        ctx.tvLog.text = text
    }

    fun startScan() {
        placeReader.startScan()
    }

    fun stopScan() {
        placeReader.stopScan()
    }

    fun newRoom() {
        DialogHelper.showDialog(ctx, "New room title:", placeReader::newRoom)
    }
}