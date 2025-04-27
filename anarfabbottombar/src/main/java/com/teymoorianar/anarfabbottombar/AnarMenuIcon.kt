package com.teymoorianar.anarfabbottombar

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * @author Mahdi Teymoori Anar
 * <p> You don't need to use this, this is used by AnarBottomBarWithFab </p>
 * <p> Licensed under GNU LGPL </p>
 */

class AnarMenuIcon @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val menuIconView: ImageView
    val menuNameView: TextView
    val menuActivationBar: ImageView
    var group: List<AnarMenuIcon> = ArrayList()
    var activeColor: ColorStateList = ColorStateList.valueOf(com.google.android.material.R.color.design_default_color_primary)
    var inactiveColor: ColorStateList= ColorStateList.valueOf(com.google.android.material.R.color.design_default_color_primary)
    var showActivationBar: Boolean = true

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_anar_menu_icon, this, true)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AnarBottomBarWithFab,
            defStyleAttr,
            0
        ).apply {
            try {
                menuIconView = findViewById(R.id.menuIcon)
                menuNameView = findViewById(R.id.menuName)
                menuActivationBar = findViewById(R.id.activationBar)
//                ImageViewCompat.setImageTintList(menuActivationBar, activeColor)


            } finally {
                recycle()
            }
        }
    }

    fun setInfo(menuItem: AnarMenuItem) {
        menuNameView.text = menuItem.title
        menuIconView.setImageResource(menuItem.iconResourceId)
        setOnClickListener{
            setActivation(true)
            menuItem.callOnClick()
        }
    }

    fun setActivation(beActive: Boolean = false) {
        if (beActive) {
            menuActivationBar.imageTintList = activeColor
            for (icon in group)
                if (icon != this) icon.setActivation(false)
        }
        menuActivationBar.visibility = if (!showActivationBar) GONE else if (beActive) VISIBLE else INVISIBLE
        menuIconView.imageTintList = if (beActive) activeColor else inactiveColor
        menuNameView.setTextColor(if (beActive) activeColor else inactiveColor)

    }
}