package org.bs.pr.bl.scanners

import org.bs.pr.interfaces.PlaceReaderListener
import org.bs.pr.model.Space


abstract class Scanner{
    var listeners = ArrayList<PlaceReaderListener>()
    var shouldScan = false

    open fun scan(){
        shouldScan = true
    }

    open fun stopScan(){
        shouldScan = false
    }

    open fun addListener(listener: PlaceReaderListener){
        listeners.add(listener)
    }
}