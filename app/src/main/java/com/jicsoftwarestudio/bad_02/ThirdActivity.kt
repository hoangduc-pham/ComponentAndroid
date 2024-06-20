package com.jicsoftwarestudio.bad_02

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jicsoftwarestudio.bad_02.databinding.ActivityThirdBinding
class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var explicitBroadcastButton: Button
    private lateinit var implicitBroadcastButton: Button
    private lateinit var sendToServiceButton: Button
    private val myBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("ThirdActivity", "Broadcast received")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val d = Log.d("ThirdActivity", "onCreate")
        sendToServiceButton = findViewById(R.id.buttonSendToService)

        sendToServiceButton.setOnClickListener {
            sendMessageToService("hello")
        }
        explicitBroadcastButton = findViewById(R.id.buttonExplicitBroadcast)
        implicitBroadcastButton = findViewById(R.id.buttonImplicitBroadcast)

        explicitBroadcastButton.setOnClickListener {
            sendExplicitBroadcast()
        }

        implicitBroadcastButton.setOnClickListener {
            sendImplicitBroadcast()
        }
    }

    override fun onStart() {
        super.onStart()
        // Register BroadcastReceiver
        val intentFilter = IntentFilter("com.example.MY_ACTION")
        registerReceiver(myBroadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myBroadcastReceiver)
    }

    private fun sendExplicitBroadcast() {
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        intent.action = "com.example.MY_ACTION"
        sendBroadcast(intent)
    }

    private fun sendImplicitBroadcast() {
        val intent = Intent()
        intent.action = "com.example.MY_ACTION"
        sendBroadcast(intent)
    }
    private fun sendMessageToService(message: String) {
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("MESSAGE", message)
        startService(intent)
    }
}