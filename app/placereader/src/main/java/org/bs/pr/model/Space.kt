package org.bs.pr.model

import org.bs.pr.model.sensors.GpsSpot
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.sensors.WifiAvailable

open class Space {
    var spots = ArrayList<Spot>()
}