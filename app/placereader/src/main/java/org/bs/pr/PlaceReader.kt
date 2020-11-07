package org.bs.pr

import android.content.Context
import org.bs.pr.bl.Scanner
import org.bs.pr.bl.gps.GpsScanner
import org.bs.pr.bl.mobile.TransmitterScanner
import org.bs.pr.bl.wifi.WifiScanner
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.Building
import org.bs.pr.model.Passage
import org.bs.pr.model.Room
import java.util.function.Consumer

class PlaceReader(ctx: Context, prl: PlaceReaderListener?) {
    var scanners = listOf(
        GpsScanner(ctx, prl),
        TransmitterScanner(ctx, prl),
        WifiScanner(ctx, prl)
    )

    //var building = Building()
    var passage = Passage()

    lateinit var room: Room

    fun startScan() {
        newRoom(null)
        scanners.forEach { x -> x.scan() }
    }

    fun stopScan() {
        scanners.forEach { x -> x.stopScan() }
    }

    fun newRoom(name: String?) {
        if (name == null) {
            scanners.forEach { x -> x.changeRoom(passage) }
        } else {
            room = Room(name)
            //building.Rooms.add(room)
            scanners.forEach { x -> x.changeRoom(room) }
        }
    }
}