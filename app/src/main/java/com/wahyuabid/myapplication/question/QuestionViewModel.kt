package com.wahyuabid.myapplication.question

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
class QuestionViewModel(
    val classPref: ClassSharedPref
) : ViewModel() {

    val shouldShowBottomSheet: MutableLiveData<Pair<Boolean, MutableList<MemberModel>>> =
        MutableLiveData()

    var selectedClass: ClassModel? = null
    var listClass: MutableList<ClassModel> = mutableListOf()

    fun onViewLoaded(classID: String) {
        selectedClass = classPref.getClass().firstOrNull { it.id == classID }

    }

    fun onInputNilai(nilaiKe: Int, memberID: String, listNilai: MutableList<Double>){
        var member = selectedClass?.listMember?.firstOrNull { it.id == memberID }
        when(nilaiKe){
            0 -> member?.tes1 = listNilai.firstOrNull()?:0.0
            1 -> member?.tes2 = listNilai.firstOrNull()?:0.0
            2 -> member?.tes3 = listNilai.firstOrNull()?:0.0
            3 -> member?.tes4 = listNilai
            4 -> member?.tes5 = listNilai.firstOrNull()?:0.0
        }
        member?.apply {
            onEditMember(this)
        }
    }

    private fun onEditMember(data: MemberModel) {
        classPref.setClass(listClass.apply {
            this.firstOrNull { it.id == selectedClass?.id }?.listMember?.firstOrNull {
                it.id == data.id
            }?.apply {
                this.name = data.name
                this.sex = data.sex
                this.placeBirth = data.placeBirth
                this.dateBirth = data.dateBirth
            }
        })
    }

    fun onClickInputNilai(questionNumber: Int) {
        shouldShowBottomSheet.value =
            Pair(questionNumber == 3, selectedClass?.listMember.orEmpty().toMutableList())
    }
}