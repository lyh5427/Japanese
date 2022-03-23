package com.example.japanese

class viewModel {
    private var status : String = ""
    private var count : Int = 0
    companion object{
        private var instance : viewModel? = null

        fun getInstance() : viewModel =
            instance ?: synchronized(this){
                instance ?: viewModel().also{
                    instance = it
                }
            }

    }
    fun insertStatus( s: String){
        status = s
    }

    fun insertCount( c : Int){
        count = c
    }

    fun getStatue() :String = status
    fun getCount() : Int = count
}