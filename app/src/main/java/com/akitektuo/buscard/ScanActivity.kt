package com.akitektuo.buscard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.akitektuo.buscard.App.Companion.database
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.zxing.BarcodeFormat
import kotlinx.android.synthetic.main.activity_scan.*

class ScanActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    private var initialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        textCancel.setOnClickListener {
            finish()
        }

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA), 1)
            return
        }

        initCamera()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    initCamera()
                } else {
                    Toast.makeText(this, "In order to scan a QR code, you must enable the camera!", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }

    private fun initCamera() {
        codeScanner = CodeScanner(this, scanner)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = listOf(BarcodeFormat.QR_CODE) // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                database.createRequest {
                    startActivity(Intent(this, SuccessActivity::class.java))
                    finish()
                }
            }
        }

        codeScanner.startPreview()
        initialized = true
    }

    override fun onResume() {
        super.onResume()
        if (initialized) {
            codeScanner.startPreview()
        }
    }

    override fun onPause() {
        if (initialized) {
            codeScanner.releaseResources()
        }
        super.onPause()
    }
}
