package org.bs.pr.controllers.mobile

import android.content.Context
import android.provider.ContactsContract
import android.service.autofill.Validators.or
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import org.bs.pr.controllers.Scanner
import org.bs.pr.listeners.PlaceReaderListener
import org.bs.pr.model.Room
import java.util.concurrent.Executor

class TransmitterScanner(context: Context, prl: PlaceReaderListener): Scanner,
    PhoneStateListener() {
    var ctx = context
    var readerListener = prl
    var shouldScan = false
    lateinit var room: Room
    var cpsl = CustomPhoneStateListener(ctx, readerListener)

    var tManager: TelephonyManager =  ctx.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


    init{
        shouldScan = true
        tManager.listen(cpsl, LISTEN_CELL_INFO or LISTEN_CELL_LOCATION or LISTEN_SIGNAL_STRENGTHS )
    }

    override fun changeRoom(newRoom: Room) {
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