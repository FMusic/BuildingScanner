package org.bs.pr.model

open class Space {
    var gpsSpots = ArrayList<GpsSpot>()
    var mobileSpots = ArrayList<MobileSpot>()
    var wifiSpots = ArrayList<ArrayList<WifiAvailable>>()

    fun addWifiSpot(wifiSpot: ArrayList<WifiAvailable>) {
        wifiSpots.add(wifiSpot)
    }
}