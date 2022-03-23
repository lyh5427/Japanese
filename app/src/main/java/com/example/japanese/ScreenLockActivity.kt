package com.example.japanese

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_screen_lock.*
import java.io.FileReader
import java.util.Random

@Suppress("DEPRECATION")
class ScreenLockActivity : AppCompatActivity() {
    private var l_count = 0
    val db : FirebaseDatabase = FirebaseDatabase.getInstance()
    var name = ""
    private val model : viewModel = viewModel.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_lock)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        supportActionBar?.hide()

        when(model.getStatue()){ //난이도에 따른 액티비티 설정
            "1"->{
                L_nanT.text = "상"
                name = "high"
                linearLayout0.visibility = View.VISIBLE
                L_count.text = l_count.toString()
            }
            "2"->{
                L_nanT.text = "중"
                name = "middle"
                linearLayout0.visibility = View.INVISIBLE
                L_count.text = l_count.toString()
                linearLayout0.visibility = View.INVISIBLE
            }
            "3"->{
                L_nanT.text = "하"
                name = "Low"
                linearLayout0.visibility = View.INVISIBLE
                L_count.text = l_count.toString()
                linearLayout0.visibility = View.INVISIBLE
            }
        }
        L_showAn.setOnClickListener{
            means.visibility = View.VISIBLE
        }
        val ran = Random()
        val num = ran.nextInt(10) + 1
        means.visibility = View.INVISIBLE

        selects(name, db, num)

        L_next.setOnClickListener{
            val ran = Random()
            val num = ran.nextInt(10) + 1
            means.visibility = View.INVISIBLE
            selects(name, db, num)
            l_count ++
            L_count.text = l_count.toString()
            if(l_count > model.getCount()){
                finish()
            }
        }
    }
    fun selects(tableName : String, db : FirebaseDatabase, num : Int){
        val readDb : DatabaseReference = db.getReference(tableName)
        readDb.get().addOnSuccessListener {
            Log.d("firebase Value : ", "22222222222222${name}")
            val map = it.child(num.toString()).getValue() as HashMap<String, String>
            when(tableName){
                "high"->{
                    L_wordT.text = map.get("word")
                    means.text = map.get("mean")
                    sounds.text = map.get("sound")
                    hira.text = map.get("hira")
                }
                "middle"->{
                    L_wordT.text = map.get("word")
                    means.text = map.get("mean")
                    sounds.text = map.get("sound")
                }
                "Low"->{
                    L_wordT.text = map.get("word")
                    means.text = map.get("mean")
                    sounds.text = map.get("sound")
                }
            }
        }
    }
}