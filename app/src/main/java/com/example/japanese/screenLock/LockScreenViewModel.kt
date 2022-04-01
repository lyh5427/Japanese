package com.example.japanese.screenLock

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.japanese.shareData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.HashMap

class LockScreenViewModel() : ViewModel() {
    private val db : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val sharD = shareData.getInstance()

    var word = MutableLiveData<String>()
    var mean = MutableLiveData<String>()
    var sound = MutableLiveData<String>()
    var hi = MutableLiveData<String>()

    var rank : MutableLiveData<String> = sharD.getRank()
    var count : MutableLiveData<String> = sharD.getCount()

    init {
        word.value = ""
        mean.value = ""
        sound.value = ""
        hi.value = ""
    }
    fun selects(){
        var tableName = ""
        when(rank.value.toString()){
            "상"-> tableName = "high"
            "중"-> tableName = "middle"
            "하"-> tableName = "Low"
            else-> tableName = "high"
        }
        val num = Random().nextInt(10) +1
        val readDb : DatabaseReference = db.getReference(tableName)
        readDb.get().addOnSuccessListener {
            val map = it.child(num.toString()).getValue() as HashMap<String, String>
            when(tableName){
                "high"->{
                    word.value = map.get("word") //단어
                    mean.value = map.get("mean") // 뜻
                    sound.value = map.get("sound") //소리
                    hi.value = map.get("hira") // 히라
                }
                "middle"->{
                    word.value = map.get("word")
                    mean.value = map.get("mean")
                    sound.value = map.get("sound")
                }
                "Low"->{
                    word.value = map.get("word")
                    mean.value = map.get("mean")
                    sound.value = map.get("sound")
                }
            }
        }
    }

}