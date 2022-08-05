package com.example.midmornigintentsapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    var buttonSms: Button? = null
    var buttonEmail: Button? = null
    var buttoncamera: Button? = null
    var buttonMpesa: Button? = null
    var buttonShare: Button? = null
    var buttonCall: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSms = findViewById(R.id.mBtnSmS)
        buttonEmail = findViewById(R.id.mBtnEmail)
        buttoncamera = findViewById(R.id.mBtnCamera)
        buttonMpesa = findViewById(R.id.mBtnMpesa)
        buttonShare = findViewById(R.id.mBtnShare)
        buttonCall = findViewById(R.id.mBtnCall)
        buttonSms!!.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:0111878293")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Niaje...")
            startActivity(intent)

        }
        buttonEmail!!.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "emobilis@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear sir")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }
        buttoncamera!!.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)

        }
        buttonMpesa!!.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }
        }
        buttonShare!!.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            startActivity(shareIntent)

        }
        buttonCall!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+254111878093"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }


        }

    }
}
