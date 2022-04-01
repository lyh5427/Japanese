package com.example.japanese.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.japanese.shareData

class StartViewModel(): ViewModel() {

    private val sharD : shareData = shareData.getInstance()
    var rank : MutableLiveData<String> = sharD.getRank()
    var count : MutableLiveData<String> = sharD.getCount()
    var status : MutableLiveData<String> = sharD.getStatus()

    fun setRank( r : String){ this.sharD.setRank(r) }

    fun setCount( c : String){ this.sharD.setCount(c) }

    fun setStatus( s: String){ this.sharD.setStatus(s) }
}