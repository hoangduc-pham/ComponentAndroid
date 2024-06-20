package com.jicsoftwarestudio.bad_02

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {

    private var serviceJob: Job? = null
    private val serviceScope = CoroutineScope(Dispatchers.IO)
    private val binder = MyBinder()

    inner class MyBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null && intent.hasExtra("MESSAGE")) {
            val message = intent.getStringExtra("MESSAGE")
            Log.d("MyService", "Received message: $message")
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob?.cancel()
        Log.d("MyService", "Service destroyed")
    }
}
