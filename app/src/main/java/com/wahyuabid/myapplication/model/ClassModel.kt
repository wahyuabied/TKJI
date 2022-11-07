package com.wahyuabid.myapplication.model

import java.util.*

data class ClassModel(
    val id: String = UUID.randomUUID().toString(),
    var name: String,
    var teacher: String,
    var date: String,
    var location: String,
    var listMember: MutableList<MemberModel> = mutableListOf()
)