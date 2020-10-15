package org.bs.pr.bl.mobile

import android.content.Context
import android.telephony.CellInfo
import android.telephony.CellLocation
import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import android.telephony.cdma.CdmaCellLocation
import android.telephony.gsm.GsmCellLocation
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.MobileSpot
import org.bs.pr.model.Room

class CustomPhoneStateListener(context: Context, rl: PlaceReaderListener) : PhoneStateListener() {
    var ctx = context
    var readerListener = rl
    var room: Room? = null
    var ms: MobileSpot? = null
    var memoryOn = false

    fun ChangeRoom(newRoom: Room) {
        if (ms != null && room != null) {
            room?.mobileSpots?.add(ms!!)
        }
        room = newRoom
        ms = MobileSpot()
    }

    fun startMemorizing() {
        memoryOn = true
    }

    fun stopMemorizing() {
        memoryOn = false
    }

    override fun onCellInfoChanged(cellInfo: MutableList<CellInfo>?) {
        super.onCellInfoChanged(cellInfo)
        cellInfo?.stream()?.map { x-> ms?.cells?.add(x) }
    }

    override fun onCellLocationChanged(location: CellLocation?) {
        super.onCellLocationChanged(location)
        if (location != null){
            ms?.locations?.add(location)
        }
    }

    override fun onSignalStrengthsChanged(signalStrength: SignalStrength?) {
        super.onSignalStrengthsChanged(signalStrength)
        if (signalStrength != null){
            ms?.strengths?.add(signalStrength)
        }
    }
}
