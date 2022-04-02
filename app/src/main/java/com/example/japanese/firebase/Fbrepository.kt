package com.example.japanese.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import java.util.*
import kotlin.collections.HashMap

class Fbrepository {
    private var fb = Fbdata.getInstance()
    private var dbInstance = fb.getDbInstace()

    private var rank : MutableLiveData<String> = MutableLiveData("클릭")
    private var count : MutableLiveData<String> = MutableLiveData("0")
    private var status : MutableLiveData<String> = MutableLiveData("OFF")

    private var word = MutableLiveData<String>()
    private var mean = MutableLiveData<String>()
    private var sound = MutableLiveData<String>()
    private var hi = MutableLiveData<String>()

    companion object{
        private var instance : Fbrepository? = null

        fun getInstance() : Fbrepository =
            instance ?: synchronized(this){
                instance ?: Fbrepository().also{
                    instance = it
                }
            }
    }

    fun selects(){
        var tableName = ""
        when(rank.value.toString()){
            "상"-> tableName = "high"
            "중"-> tableName = "middle"
            "하"-> tableName = "Low"
        }
        val num = Random().nextInt(10) +1
        val readDb : DatabaseReference = dbInstance.getReference(tableName)
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

    fun setRank(r : String) { this.rank.value = r }
    fun setCount(c : String) { this.count.value = c }
    fun setStatus(s : String) { this.status.value = s }

    fun getRank() : MutableLiveData<String> = this.rank!!
    fun getStatus() : MutableLiveData<String> = this.status!!
    fun getCount() : MutableLiveData<String> = this.count!!
    fun getWord() : MutableLiveData<String> = this.word!!
    fun getSound() : MutableLiveData<String> = this.sound!!
    fun getHi() : MutableLiveData<String> = this.hi!!
    fun getMean() : MutableLiveData<String> = this.mean!!
}