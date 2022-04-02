package com.example.japanese.firebase

import com.google.firebase.database.FirebaseDatabase

class Fbdata {

    private val dbInstance : FirebaseDatabase = FirebaseDatabase.getInstance()

    companion object{
        private var instance : Fbdata? = null
        fun getInstance() : Fbdata =
            instance ?: synchronized(this){
                instance ?: Fbdata().also{
                    instance = it
                }
            }
    }

    fun getDbInstace() = dbInstance
}