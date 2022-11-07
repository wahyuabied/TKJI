package com.wahyuabid.myapplication.model

import java.util.*

data class MemberModel(
    val id: String = UUID.randomUUID().toString(),
    var name: String,
    var placeBirth: String,
    var dateBirth: Double,
    var sex: String,
    var tes1: Double = 0.0,
    var tes2: Double = 0.0,
    var tes3: Double = 0.0,
    var tes4: MutableList<Double> = mutableListOf(0.0,0.0,0.0),
    var tes5: Double = 0.0
)