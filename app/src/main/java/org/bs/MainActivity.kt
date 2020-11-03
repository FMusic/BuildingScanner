package org.bs

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.*
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import org.bs.activities.ScanActivity

class MainActivity : AppCompatActivity(),
    PermissionRequestErrorListener, MultiplePermissionsListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnStartActClick(v: View) {
        startActivity(Intent(applicationContext, ScanActivity::class.java))
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .withListener(this)
            .withErrorListener(this)
            .check()
    }

    override fun onError(p0: DexterError?) {
        Log.e("Dexter", "Dexter error: " + p0.toString())
    }

    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
    }

    override fun onPermissionRationaleShouldBeShown(
        p0: MutableList<PermissionRequest>?,
        p1: PermissionToken?
    ) {
        p1?.continuePermissionRequest()
    }
}