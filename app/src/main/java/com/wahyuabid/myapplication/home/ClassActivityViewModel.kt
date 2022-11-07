package com.wahyuabid.myapplication.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyuabid.myapplication.model.ClassModel
import com.wahyuabid.myapplication.shared_preferences.ClassSharedPref

/**
 * Created by Wahyu Abid A on 14/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class ClassActivityViewModel(
    val classPref: ClassSharedPref
): ViewModel(){

    val shouldShowEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val shouldShowData: MutableLiveData<MutableList<ClassModel>> = MutableLiveData()

    var listClass = mutableListOf<ClassModel>()

    fun onViewLoaded(){
        listClass = classPref.getClass()
        onPopulateViewModel()
    }

    fun onAddClass(data: ClassModel){
        listClass.add(data)
        classPref.setClass(listClass)
        onViewLoaded()
    }

    fun onEditClass(data: ClassModel){
        var newListClass = mutableListOf<ClassModel>()
        listClass.forEach {
            if(it.id == data.id){
                newListClass.add(data)
            }else{
                newListClass.add(it)
            }
        }
        Log.e("Abid",newListClass.toString())
        classPref.setClass(newListClass)
        onViewLoaded()
    }

    fun onDeleteClass(id: String){
        var newListClass = mutableListOf<ClassModel>()
        newListClass.addAll(listClass.filter { it.id != id })
        classPref.setClass(newListClass)
        onViewLoaded()
    }

    fun onPopulateViewModel(){
        if(listClass.isEmpty()){
            shouldShowEmpty.value = true
        }else{
            shouldShowEmpty.value = false
            shouldShowData.value = listClass
        }
    }

}