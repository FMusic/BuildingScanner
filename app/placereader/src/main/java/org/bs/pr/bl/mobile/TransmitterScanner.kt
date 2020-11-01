package org.bs.pr.bl.mobile

import android.content.Context
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import org.bs.pr.bl.Scanner
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.Room
import org.bs.pr.model.Space

class TransmitterScanner(context: Context, prl: PlaceReaderListener?): Scanner,
    PhoneStateListener() {
    var ctx = context
    var readerListener = prl
    var shouldScan = false
    lateinit var room: Space
    var cpsl = CustomPhoneStateListener(ctx, readerListener)

    private val events = (LISTEN_CELL_INFO or LISTEN_CELL_LOCATION or LISTEN_SIGNAL_STRENGTHS)

    var tManager: TelephonyManager =  ctx.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


    init{
        shouldScan = true
        tManager.listen(cpsl, events )
    }

    override fun changeRoom(newRoom: Space) {
        room = newRoom
        cpsl.ChangeRoom(newRoom)
    }

    override fun scan() {
        cpsl.startMemorizing()
    }

    override fun stopScan() {
        cpsl.stopMemorizing()
    }


}