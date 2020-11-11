package org.bs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.bs.scan.ScanActivity
import org.bs.views.DialogHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnStartActClick(v: View) {
        DialogHelper.showDialog(this, "Input building name", ::startScanActivity)
    }

    private fun startScanActivity(va: String?){
        if (va != null){
            var i = Intent(applicationContext, ScanActivity::class.java)
            i.putExtra(getString(R.string.ibuildingname), va)
            startActivity(i)
        }
    }
}