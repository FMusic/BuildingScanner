package org.bs.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import org.bs.R
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.GpsSpot
import org.bs.pr.model.MobileSpot
import org.bs.pr.model.Room
import org.bs.pr.model.WifiAvailable

class ScanActivity : AppCompatActivity(), PlaceReaderListener {
    lateinit var btnStartScan: Button
    lateinit var btnStopScan: Button
    lateinit var btnNewRoom: Button
    lateinit var lvRoomScanned: ListView
    lateinit var tvLog: TextView
    lateinit var tvRoomName: TextView

    var arrRooms = ArrayList<String>()
    var adapter = ArrayAdapter(this, R.layout.view_list_rooms, R.id.tvRoomName, arrRooms)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWidgets()
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
    }

    fun btnStopClick(view: View) {

    }
    fun btnNewRoom(view: View) {

    }

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