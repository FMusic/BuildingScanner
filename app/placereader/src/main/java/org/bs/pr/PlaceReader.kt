package org.bs.pr

import android.content.Context
import org.bs.pr.bl.collector.DataCollector
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.Building
import org.bs.pr.model.Room

class PlaceReader(ctx: Context, prl: PlaceReaderListener, buildingName: String) {
    var building = Building(buildingName)
    var dataCollector = DataCollector(ctx, prl, building)

    fun startScan() {
        newRoom(null)
    }

    fun stopScan() {
        dataCollector.stopScan();
    }

    fun newRoom(name: String?) {
        dataCollector.newRoom(name)
    }
}