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
import org.bs.presenters.ScanPresenter
import org.bs.views.DialogHelper

class ScanActivity : AppCompatActivity() {
    lateinit var btnStartScan: Button
    lateinit var btnStopScan: Button
    lateinit var btnNewRoom: Button
    lateinit var lvRoomScanned: ListView
    lateinit var tvLog: TextView
    lateinit var tvRoomName: TextView

    lateinit var scanPresenter: ScanPresenter

    var arrRooms = ArrayList<String>()
    lateinit var adapter: ArrayAdapter<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ArrayAdapter(this, R.layout.view_list_rooms, R.id.tvRoomName,
            arrRooms as List<String?>)
        setWidgets()
        scanPresenter = ScanPresenter(this)
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
        scanPresenter.startScan()
    }

    fun btnStopClick(view: View) {
        scanPresenter.stopScan()
    }

    fun btnNewRoom(view: View) {
        scanPresenter.newRoom()
    }

}