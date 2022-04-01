package com.example.japanese

import androidx.lifecycle.MutableLiveData

class shareData {
    private var rank : MutableLiveData<String> = MutableLiveData("클릭")
    private var count : MutableLiveData<String> = MutableLiveData("0")
    private var status : MutableLiveData<String> = MutableLiveData("OFF")

    companion object{
        private var instance : shareData? = null

        fun getInstance() : shareData =
            instance ?: synchronized(this){
                instance ?: shareData().also{
                    instance = it
                }
            }
    }

    fun setRank( s: String){ this.rank.value = s }

    fun setCount( c: String){ this.count.value = c }

    fun setStatus( s: String){ this.status.value = s}

    fun getRank() = this.rank
    fun getCount() = this.count
    fun getStatus() = this.status
}