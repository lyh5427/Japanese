package com.example.japanese

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_start.*

@Suppress("DEPRECATION")
class start : AppCompatActivity() {
    private var ON_OFF_STATE = 0
    lateinit var sqlDB : SQLiteDatabase
    private val model : viewModel = viewModel.getInstance()

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
        setContentView(R.layout.activity_start)

        when(model.getStatue()){
            "1" -> nanB.text = "상"
            "2" -> nanB.text = "중"
            "3" -> nanB.text = "하"
        }
        when(model.getCount()){
            1->countB.text = "1개"
            2->countB.text = "2개"
            3->countB.text = "3개"
            4->countB.text = "4개"
            5->countB.text = "5개"
        }
        when(ON_OFF_STATE){
            0 -> ofB.text = "OFF"
            1 -> ofB.text = "ON"
        }

        nanB.setOnClickListener{
            val items = arrayOf("상", "중", "하")
            val builder =  AlertDialog.Builder(this)
            builder.setTitle("난이도 선택")
            builder.setItems(items){dialog, which ->
                when(which){
                    0->{
                        nanB.text = items[which]
                        model.insertStatus( "1")
                    }
                    1->{
                        nanB.text = items[which]
                        model.insertStatus( "2")

                    }
                    2->{
                        nanB.text = items[which]
                        model.insertStatus( "3")
                    }
                }
            }
            builder.show()
        }

        ofB.setOnClickListener{
            if(ofB.text == "ON"){
                ofB.text = "OFF"
                ON_OFF_STATE = 0
            }else if(ofB.text == "OFF"){
                ofB.text = "ON"
                ON_OFF_STATE = 1
            }
        }

        countB.setOnClickListener{
            val items = arrayOf("1개", "2개", "3개", "4개", "5개")
            val builder =  AlertDialog.Builder(this)
            builder.setTitle("잠금해제 개수 선택")
            builder.setItems(items){dialog, which ->
                when(which){
                    0->{
                        countB.text = items[which]
                        model.insertCount( which+1)
                    }
                    1->{
                        countB.text = items[which]
                        model.insertCount( which+1)
                    }
                    2->{
                        countB.text = items[which]
                        model.insertCount( which+1)
                    }
                    3->{
                        countB.text = items[which]
                        model.insertCount( which+1)
                    }
                    4->{
                        countB.text = items[which]
                        model.insertCount( which+1)
                    }
                }
            }
            builder.show()
        }

        btn_set.setOnClickListener{
            if(ON_OFF_STATE == 0){//설정 끔
                var intent = Intent(applicationContext, ScreenService::class.java)
                stopService(intent)
            }else if(ON_OFF_STATE == 1){ // 설정 킴
                checkPermission()
            }
        }
    }


}