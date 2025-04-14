package com.teymoorianar.anar_bottom_bar_with_fab

interface AnarMenuItem {
    var iconResourceId: Int
    var title: String
    fun callOnClick()
}