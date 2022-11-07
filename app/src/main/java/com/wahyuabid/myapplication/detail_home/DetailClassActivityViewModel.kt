package com.wahyuabid.myapplication.detail_home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyuabid.myapplication.model.ClassModel
import com.wahyuabid.myapplication.model.MemberModel
import com.wahyuabid.myapplication.shared_preferences.ClassSharedPref

/**
 * Created by Wahyu Abid A on 14/03/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
class DetailClassActivityViewModel(
    val classPref: ClassSharedPref
) : ViewModel() {

    val shouldShowEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val shouldShowData: MutableLiveData<MutableList<MemberModel>> = MutableLiveData()
    val shouldShowTitle: MutableLiveData<String> = MutableLiveData()
    val shouldShowCountTitle: MutableLiveData<String> = MutableLiveData()
    val shouldDownload: MutableLiveData<ClassModel> = MutableLiveData()

    var classModel: ClassModel? = null
    var listClass: MutableList<ClassModel> = mutableListOf()
    var memberID = ""

    fun onViewLoaded(id: String) {
        this.memberID = id
        listClass = classPref.getClass()
        classModel = listClass.firstOrNull { it.id == id }
        shouldShowCountTitle.value = "Daftar Peserta Tes(${classModel?.listMember?.size ?: 0})"
        shouldShowTitle.value = classModel?.name
        onPopulateViewModel()
    }

    fun onAddMember(data: MemberModel) {
        classPref.setClass(listClass.apply {
            this.firstOrNull { it.id == classModel?.id }?.listMember?.add(data)
        })
        onViewLoaded(memberID)
    }

    fun onEditMember(data: MemberModel) {
        classPref.setClass(listClass.apply {
            this.firstOrNull { it.id == classModel?.id }?.listMember?.firstOrNull {
                it.id == data.id
            }?.apply {
                this.name = data.name
                this.sex = data.sex
                this.placeBirth = data.placeBirth
                this.dateBirth = data.dateBirth
            }
        })
        onViewLoaded(memberID)
    }

    fun onDeleteMember(id: String) {
        var indexMember = 0
        classModel?.listMember?.mapIndexed { index, memberModel ->
            if(memberModel.id == id){
                indexMember = index
            }
        }
        classPref.setClass(listClass.apply {
            this.firstOrNull { it.id == classModel?.id }?.listMember?.removeAt(indexMember)
        }).also {
            onViewLoaded(memberID)
        }
    }

    fun onDownloadExcel(){
        shouldDownload.value = classModel
    }

    fun onPopulateViewModel() {
        if (classModel?.listMember?.isEmpty() ?: false) {
            shouldShowEmpty.value = true
        } else {
            shouldShowEmpty.value = false
            shouldShowData.value = classModel?.listMember
        }
    }

}