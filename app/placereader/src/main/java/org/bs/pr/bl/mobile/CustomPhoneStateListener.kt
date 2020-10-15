package org.bs.pr.bl.mobile

import android.content.Context
import android.telephony.CellInfo
import android.telephony.CellLocation
import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.Room

class CustomPhoneStateListener(context: Context, rl: PlaceReaderListener) : PhoneStateListener() {
    var ctx = context
    var readerListener = rl
    lateinit var room: Room
    var memoryOn = false

    fun ChangeRoom(newRoom: Room) {
        room = newRoom
    }

    fun startMemorizing() {
        memoryOn = true
    }

    fun stopMemorizing() {
        memoryOn = false
    }

    override fun onCellInfoChanged(cellInfo: MutableList<CellInfo>?) {
        super.onCellInfoChanged(cellInfo)
        TODO("implement this")
    }

    override fun onCellLocationChanged(location: CellLocation?) {
        super.onCellLocationChanged(location)
        TODO("implement this")
    }

    override fun onSignalStrengthsChanged(signalStrength: SignalStrength?) {
        super.onSignalStrengthsChanged(signalStrength)
        TODO("implement this")
    }
}
