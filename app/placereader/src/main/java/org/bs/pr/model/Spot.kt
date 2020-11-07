package org.bs.pr.model

import org.bs.pr.model.sensors.GpsSpot
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.sensors.WifiAvailable
import java.time.LocalDate

class Spot {
    lateinit var gpsSpot: GpsSpot
    lateinit var mobileSpot: MobileSpot
    lateinit var wifiAvailable: WifiAvailable

    var timestamp = LocalDate.now()
}