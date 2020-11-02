package org.bs.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import org.bs.R
import org.bs.pr.PlaceReader
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.GpsSpot
import org.bs.pr.model.MobileSpot
import org.bs.pr.model.Room
import org.bs.pr.model.WifiAvailable
import org.bs.views.DialogHelper

class ScanActivity : AppCompatActivity(), PlaceReaderListener {
    lateinit var btnStartScan: Button
    lateinit var btnStopScan: Button
    lateinit var btnNewRoom: Button
    lateinit var lvRoomScanned: ListView
    lateinit var tvLog: TextView
    lateinit var tvRoomName: TextView
    lateinit var placeReader: PlaceReader

    var arrRooms = ArrayList<String>()
    lateinit var adapter: ArrayAdapter<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ArrayAdapter(this, R.layout.view_list_rooms, R.id.tvRoomName,
            arrRooms as List<String?>)
        setWidgets()
        placeReader = PlaceReader(this, this)
    }

    private fun setWidgets() {
        //setContentView(R.layout.activity_scan)
        setContentView(R.layout.activity_scan_simple)
        btnStartScan = findViewById(R.id.btnStartScan)
        btnStopScan = findViewById(R.id.btnStopScan)
        btnNewRoom = findViewById(R.id.btnNewRoom)
        tvLog = findViewById(R.id.tvLog)
        lvRoomScanned = findViewById(R.id.lvRoomScanned)
        setList()
    }

    private fun setList() {
        lvRoomScanned.adapter = adapter;
        refreshAdapter()
    }

    private fun refreshAdapter() {
        adapter.notifyDataSetChanged()
    }

    fun btnStartClick(view: View) {
        placeReader.startScan()
    }

    fun btnStopClick(view: View) {
        placeReader.stopScan()
    }
    fun btnNewRoom(view: View) {
        placeReader.newRoom(DialogHelper().showDialog(this, getString(R.string.new_room_title)))
    }

    override fun onRoomScanned(r: Room) {
        var text = tvLog.text.toString()
        text += r.roomName + "finished"
        tvLog.text = text
    }

    override fun onRoomStarted(r: Room) {
        var text = tvLog.text.toString()
        text += r.roomName + "started"
        tvLog.text = text
    }

    override fun onGpsChange(gpsSpot: GpsSpot) {
        var text = tvLog.text.toString()
        text += "New gps: " + gpsSpot.altitude + " " + gpsSpot.latitude + " " + gpsSpot.longitude
        tvLog.text = text
    }

    override fun onMobileSpotChange(mobileSpot: MobileSpot) {
        var text = tvLog.text.toString()
        text += "Loaded new mobile spot"
        tvLog.text = text
    }

    override fun onNewWifiDetected(wifiAvailable: WifiAvailable) {
        var text = tvLog.text.toString()
        text += "new wifi detected ${wifiAvailable.SSID}"
        tvLog.text = text
    }
}