package org.bs.pr.bl.scanners

import android.content.Context
import android.net.wifi.ScanResult
import org.bs.pr.Constants
import org.bs.pr.model.Space
import org.bs.pr.model.sensors.WifiAvailable
import java.lang.Thread.sleep
import android.net.wifi.WifiManager as WifiManager

class WifiScanner(
    private var ctx: Context
) : Scanner() {
    private var wifiManager: WifiManager
    lateinit var room: Space

    init{
        shouldScan = true
        wifiManager = ctx.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun scan(){
        super.scan()
        while(shouldScan){
            process(wifiManager.scanResults)
            sleep(Constants.SLEEP_TIME_MS.toLong())
        }
    }

    private fun process(scanResults: List<ScanResult>) {
        val wifiSpots: ArrayList<WifiAvailable> = ArrayList()
        for (entry in scanResults){
            var wa = WifiAvailable(entry.SSID, entry.level)
            wifiSpots.add(wa)
        }
//        room.addWifiSpot(wifiSpots)
        for (spot in wifiSpots){
            TODO()
        }
    }
}