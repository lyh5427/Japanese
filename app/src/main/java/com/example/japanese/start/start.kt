package com.example.japanese.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.japanese.R
import com.example.japanese.screenLock.ScreenService
import com.example.japanese.databinding.ActivityStartBinding
@Suppress("DEPRECATION")
class start : AppCompatActivity() {
    private var ON_OFF_STATE = 0
    private lateinit var Sbinding : ActivityStartBinding
    private val model : StartViewModel by viewModels()

    fun checkPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(this)) {
                val uri = Uri.fromParts("package", packageName, null)
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
                startActivityForResult(intent, 0)
            } else {
                val intent = Intent(applicationContext, ScreenService::class.java)
                startForegroundService(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0) {
            if(!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "해라", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(applicationContext, ScreenService::class.java)
                startForegroundService(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testFunction()
        Sbinding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        Sbinding.lifecycleOwner = this
        Sbinding.startvm = model

        Sbinding.nanB.setOnClickListener{
            val items = arrayOf("상", "중", "하")
            val builder =  AlertDialog.Builder(this)
            builder.setTitle("난이도 선택")
            builder.setItems(items){dialog, which ->
                when(which){
                    0->{
                        model.setRank("상")
                    }
                    1->{
                        model.setRank("중")
                    }
                    2->{
                        model.setRank("하")
                    }
                }
            }
            builder.show()
        }
        Sbinding.ofB.setOnClickListener{
            if(Sbinding.ofB.text == "ON"){
                Sbinding.ofB.text = "OFF"
                ON_OFF_STATE = 0
            }else if(Sbinding.ofB.text == "OFF"){
                Sbinding.ofB.text = "ON"
                ON_OFF_STATE = 1
            }
        }

        Sbinding.countB.setOnClickListener{
            val items = arrayOf("1개", "2개", "3개", "4개", "5개")
            val builder =  AlertDialog.Builder(this)
            builder.setTitle("잠금해제 개수 선택")
            builder.setItems(items){dialog, which ->
                when(which){
                    in 0..4->{
                        model.setCount((which+1).toString())
                    }
                }
            }
            builder.show()
        }

        Sbinding.btnSet.setOnClickListener{
            if(ON_OFF_STATE == 0){//설정 끔
                model.setStatus("OFF")
                var intent = Intent(applicationContext, ScreenService::class.java)
                stopService(intent)
            }else if(ON_OFF_STATE == 1){ // 설정 킴
                model.setStatus("ON")
                checkPermission()
            }
        }
    }

    fun testFunction(){
        println("A")
    }

    fun action(){
        println("S")
    }

    fun s(){
        
    }
    //s

    //ssss
}