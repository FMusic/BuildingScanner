package org.bs.pr.bl.mobile

import android.content.Context
import android.telephony.CellInfo
import android.telephony.CellLocation
import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.sensors.MobileSpot
import org.bs.pr.model.Space

class CustomPhoneStateListener(var ctx: Context, var readerListener: List<PlaceReaderListener>) : PhoneStateListener() {
    var room: Space? = null
    var ms: MobileSpot? = null
    var memoryOn = false

    fun changeRoom(newRoom: Space) {
        if (ms != null && room != null) {
//            room?.mobileSpots?.add(ms!!)
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
        cellInfo?.stream()?.map { x -> ms?.cells?.add(x) }
        if (ms != null){
            readerListener?.onMobileSpotChange(ms!!)
        }
    }

    override fun onCellLocationChanged(location: CellLocation?) {
        super.onCellLocationChanged(location)
        if (location != null) {
            if (ms != null){
                ms?.locations?.add(location)
                readerListener?.onMobileSpotChange(ms!!)
            }
        }
    }

    override fun onSignalStrengthsChanged(signalStrength: SignalStrength?) {
        super.onSignalStrengthsChanged(signalStrength)
        if (signalStrength != null) {
            if (ms != null){
                ms?.strengths?.add(signalStrength)
                readerListener?.onMobileSpotChange(ms!!)
            }
        }
    }
}
