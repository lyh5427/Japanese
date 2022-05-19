package com.example.japanese.screenLock

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.japanese.firebase.Fbrepository

class LockScreenViewModel() : ViewModel() {

    private val repository = Fbrepository.getInstance()

    var word : MutableLiveData<String> = repository.getWord()
    var mean = repository.getMean()
    var sound = repository.getSound()
    var hi = repository.getHi()

    var rank : MutableLiveData<String> = repository.getRank()
    var count : MutableLiveData<String> = repository.getCount()

    fun next(){
        repository.selects()
    }

    fun print(){
        Log.d("aa", " aaaaaaaaaaaa")
    }

}