package com.teymoorianar.anarfabbottombar

interface AnarMenuItem {
    var iconResourceId: Int
    var title: String
    fun callOnClick()
}