package com.jicsoftwarestudio.bad_02
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.content.ServiceConnection
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.jicsoftwarestudio.bad_02.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var myService: MyService? = null
    private var serviceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.MyBinder
            myService = binder.getService()
            serviceBound = true
            Log.d("SecondActivity", "Service bound")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceBound = false
            Log.d("SecondActivity", "Service unbound")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("SecondActivity", "onCreate")

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
        binding.buttonStartService.setOnClickListener {
            startMyService()
        }

        binding.buttonStopService.setOnClickListener {
            stopMyService()
        }

        binding.buttonBindService.setOnClickListener {
            bindMyService()
        }

        binding.buttonUnbindService.setOnClickListener {
            unbindMyService()
        }
    }

    private fun startMyService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

    private fun stopMyService() {
        val serviceIntent = Intent(this, MyService::class.java)
        stopService(serviceIntent)
    }

    private fun bindMyService() {
        val serviceIntent = Intent(this, MyService::class.java)
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun unbindMyService() {
        if (serviceBound) {
            unbindService(serviceConnection)
            serviceBound = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindMyService()
    }
}