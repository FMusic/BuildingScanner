package org.bs.pr

import android.content.Context
import org.bs.pr.bl.DataCollector
import org.bs.pr.interfaces.PlaceReaderListener
import org.bs.pr.model.Building

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