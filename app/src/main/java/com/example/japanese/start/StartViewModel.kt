package com.example.japanese.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.japanese.firebase.Fbrepository

class StartViewModel(): ViewModel() {

    private val repository :Fbrepository = Fbrepository.getInstance()
    var rank : MutableLiveData<String> = repository.getRank()
    var count : MutableLiveData<String> = repository.getCount()
    var status : MutableLiveData<String> = repository.getStatus()

    fun setRank( r : String){ this.repository.setRank(r) }

    fun setCount( c : String){ this.repository.setCount(c) }

    fun setStatus( s: String){ this.repository.setStatus(s) }
}