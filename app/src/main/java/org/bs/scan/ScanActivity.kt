package org.bs.scan

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

import org.bs.R

class ScanActivity : AppCompatActivity(), MultiplePermissionsListener,
    PermissionRequestErrorListener {
    lateinit var btnStartScan: Button
    lateinit var btnStopScan: Button
    lateinit var btnNewRoom: Button
    lateinit var lvRoomScanned: ListView
    lateinit var tvLog: TextView
    lateinit var tvRoomName: TextView
    lateinit var buildingName: String

    private lateinit var scanPresenter: ScanPresenter

    var arrRooms = ArrayList<String>()
    lateinit var adapter: ArrayAdapter<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildingName = intent.getStringExtra(getString(R.string.ibuildingname))
        adapter = ArrayAdapter(this, R.layout.view_list_rooms, R.id.tvRoomName,
            arrRooms as List<String?>)
        setWidgets()
        askPermissions()
    }

    private fun setList() {
        lvRoomScanned.adapter = adapter
        refreshAdapter()
    }

    private fun refreshAdapter() {
        adapter.notifyDataSetChanged()
    }

    fun btnStopClick() {
        scanPresenter.stopScan()
    }

    fun btnNewRoom() {
        scanPresenter.newRoom()
    }

    private fun askPermissions() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .withListener(this)
            .withErrorListener(this)
            .onSameThread()
            .check()
    }

    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
        scanPresenter = ScanPresenter(this)
    }

    override fun onPermissionRationaleShouldBeShown(
        p0: MutableList<PermissionRequest>?,
        p1: PermissionToken?
    ) {
        p1?.continuePermissionRequest()
    }

    override fun onError(p0: DexterError?) {
        Log.e("Dexter", "Dexter error: " + p0.toString())
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

    fun btnStartClick(v: View) {
        btnStartScan.isEnabled = false
        btnStopScan.isEnabled = true
        btnNewRoom.isEnabled = true
        scanPresenter.startScan()
    }

}