package com.example.japanese.screenLock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.japanese.R
import com.example.japanese.databinding.ActivityScreenLockBinding
import com.google.firebase.database.*

@Suppress("DEPRECATION")
class ScreenLockActivity : AppCompatActivity() {
    private var l_count = 0
    private lateinit var lBinding : ActivityScreenLockBinding
    private val model : LockScreenViewModel by viewModels()
    val db : FirebaseDatabase = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lBinding = DataBindingUtil.setContentView(this, R.layout.activity_screen_lock)
        lBinding.lifecycleOwner = this
        lBinding.lockScreenVM = model

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        supportActionBar?.hide()
        when(model.rank.value){ //난이도에 따른 액티비티 설정
            "상"->{
                lBinding.linearLayout0.visibility = View.VISIBLE
            }
            "중"->{
                lBinding.linearLayout0.visibility = View.INVISIBLE
                lBinding.linearLayout0.visibility = View.INVISIBLE
            }
            "하"->{
                lBinding.linearLayout0.visibility = View.INVISIBLE
                lBinding.linearLayout0.visibility = View.INVISIBLE
            }
        }
        lBinding.LShowAn.setOnClickListener{
            lBinding.means.visibility = View.VISIBLE
        }
        lBinding.means.visibility = View.INVISIBLE

        lBinding.LNext.setOnClickListener{
            lBinding.means.visibility = View.INVISIBLE
            l_count ++
            model.selects()
            if(l_count > Integer.parseInt(model.count.value!!)){
                finish()
            }
        }
    }

}