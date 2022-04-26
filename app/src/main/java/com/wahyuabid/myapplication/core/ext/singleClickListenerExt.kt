package com.wahyuabid.myapplication.core.ext

import android.os.SystemClock
import android.view.View

/**
 * Created by Wahyu Abid A on 05/04/22
 * Email : wahyu.abied@gmail.com
 * Github: https://github.com/wahyuabied/
 */
fun View.setOnSingleClickListener(onSingleClick: (View) -> Unit) {
    val safeClickListener = SingleClickListener {
        onSingleClick(it)
    }
    setOnClickListener(safeClickListener)
}

class SingleClickListener(private var interval: Int = 800, private val onSingleClick: (View) -> Unit) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        val currentClickTime: Long = SystemClock.elapsedRealtime()
        if ((currentClickTime - lastTimeClicked) < interval) {
            return
        }
        lastTimeClicked = currentClickTime
        onSingleClick(v)
    }
}