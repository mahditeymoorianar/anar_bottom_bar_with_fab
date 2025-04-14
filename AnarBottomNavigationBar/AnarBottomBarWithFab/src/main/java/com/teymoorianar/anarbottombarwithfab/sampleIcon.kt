package com.teymoorianar.anar_bottom_bar_with_fab

import android.util.Log

object sampleIcon: AnarMenuItem {
    override var iconResourceId: Int = R.drawable.ic_home_black_24dp
    override var title: String = "Home"

    override fun callOnClick() {
        Log.d("SAG", "callOnClick: salam")
    }
}