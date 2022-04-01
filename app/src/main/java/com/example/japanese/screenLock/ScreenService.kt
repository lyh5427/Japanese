package com.example.japanese.screenLock

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.example.japanese.MainActivity
import com.example.japanese.R

class ScreenService: Service() {

    private val receiver = object : BroadcastReceiver(){
        override fun onReceive(context : Context?, intent: Intent?) {
            if(intent != null){
                when(intent.action){
                    Intent.ACTION_SCREEN_OFF->{
                        val newIntent = Intent(context, ScreenLockActivity::class.java)
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(newIntent)
                    }
                }
            }
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    private final val ALRAM_ID = "com.bluewhale.lockscreentest.lockscreen"

    @SuppressLint("ServiceCast")
    override fun onCreate() {
        super.onCreate()

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(ALRAM_ID, "잠금화면", NotificationManager.IMPORTANCE_DEFAULT)

        nm.createNotificationChannel(channel)

        val pending = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_CANCEL_CURRENT)
        val notification = Notification.Builder(this, ALRAM_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("잠금화면 서비스")
                .setContentText("잠금화면 서비스 동작중")
                .setContentIntent(pending)
                .build()

        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        var filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_SCREEN_ON)
        registerReceiver(receiver, filter)

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}