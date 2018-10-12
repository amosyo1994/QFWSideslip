package com.amosyo.floatwindow

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.amosyo.qfloat.api.QFWSS
import com.amosyo.qfloat.enums.QLocation
import com.amosyo.qfloat.utils.QFloatPermissionUtils

class MainActivity : AppCompatActivity() {

    companion object {
        val CODE_REQUEST_OVER_LAY = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun start(view: View) {
        start()
    }

    private fun start() {
        if (!QFloatPermissionUtils.hasOverlayPermission(this)) {
            QFloatPermissionUtils.requestOverlayPermission(this, CODE_REQUEST_OVER_LAY)
            return
        }
        QFWSS.create(this)
                .setIndicatorView(R.layout.view_indicator)
                .setContentView(R.layout.view_content)
                .setContentLayoutBackgroundColor(Color.parseColor("#33000000"))
                .setIndicatorLocation(QLocation.LOCATION_SCREEN_RIGHT)
                .show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CODE_REQUEST_OVER_LAY -> start()
        }
    }
}
