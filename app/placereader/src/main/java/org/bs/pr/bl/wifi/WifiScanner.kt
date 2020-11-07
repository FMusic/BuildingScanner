package org.bs.pr.bl.wifi

import android.content.Context
import android.net.wifi.ScanResult
import org.bs.pr.Constants
import org.bs.pr.bl.Scanner
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.Space
import org.bs.pr.model.sensors.WifiAvailable
import java.lang.Thread.sleep
import android.net.wifi.WifiManager as WifiManager

class WifiScanner(private var ctx: Context, private var readerListener: PlaceReaderListener?) : Scanner {
    private var shouldScan = false
    private var wifiManager: WifiManager
    lateinit var room: Space

    init{
        shouldScan = true
        wifiManager = ctx.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun changeRoom(newRoom: Space){
        room = newRoom
    }

    override fun scan(){
        while(shouldScan){
//            process(wifiManager.scanResults)
            sleep(Constants.SLEEP_TIME_MS.toLong())
        }
    }

    override fun stopScan() {
        shouldScan = false
    }

    private fun process(scanResults: List<ScanResult>) {
        val wifiSpots: ArrayList<WifiAvailable> = ArrayList()
        for (entry in scanResults){
            var wa = WifiAvailable(entry.SSID, entry.level)
            wifiSpots.add(wa)
        }
//        room.addWifiSpot(wifiSpots)
        for (spot in wifiSpots){
            readerListener?.onNewWifiDetected(spot)
        }
    }
}