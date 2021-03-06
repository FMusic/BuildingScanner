package org.bs.pr.bl.scanners

import android.content.Context
import android.telephony.PhoneStateListener
import android.telephony.PhoneStateListener.*
import android.telephony.TelephonyManager
import org.bs.pr.interfaces.PlaceReaderListener
import org.bs.pr.model.Space

class TransmitterScanner(
    ctx: Context
): Scanner(){
    var cpsl = CustomPhoneStateListener(ctx)
    private val events = (LISTEN_CELL_INFO or LISTEN_CELL_LOCATION or LISTEN_SIGNAL_STRENGTHS)
    var tManager: TelephonyManager =  ctx.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


    init{
        tManager.listen(cpsl, events )
    }

    override fun scan() {
        super.scan()
        cpsl.memoryOn = true
    }

    override fun stopScan(){
        super.stopScan()
        cpsl.memoryOn = false
    }

    override fun addListener(listener: PlaceReaderListener) {
        super.addListener(listener)
        cpsl.addListener(listener)
    }


}