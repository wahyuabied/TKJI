package com.wahyuabid.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Wahyu Abid A on 14/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class MainActivityViewModel(): ViewModel(){

    val shouldShowMessage: MutableLiveData<String> = MutableLiveData()

    private var username: String = ""
    private var password: String = ""

    fun onChangeUsername(value: String){
        username = value
    }

    fun onChangePass(value: String){
        password = value
    }

    fun onButtonConfirm(){
        if(username == "admin" && password == "admin"){
            shouldShowMessage.value = "Benar"
        }else{
            shouldShowMessage.value = "Salah"
        }
    }
}