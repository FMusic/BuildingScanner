package org.bs.presenters

import android.content.Context
import org.bs.R
import org.bs.activities.ScanActivity
import org.bs.pr.PlaceReader
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.GpsSpot
import org.bs.pr.model.MobileSpot
import org.bs.pr.model.Room
import org.bs.pr.model.WifiAvailable
import org.bs.views.DialogHelper

class ScanPresenter(scanActivity: ScanActivity) : PlaceReaderListener {
    var placeReader = PlaceReader(scanActivity, this)
    var ctx: ScanActivity = scanActivity


    override fun onRoomScanned(r: Room) {
        var text = ctx.tvLog.text.toString()
        text += r.roomName + "finished"
        ctx.tvLog.text = text
    }

    override fun onRoomStarted(r: Room) {
        var text = ctx.tvLog.text.toString()
        text += r.roomName + "started"
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
        placeReader.newRoom(DialogHelper().showDialog(ctx, ctx.getString(R.string.new_room_title)))
    }
}