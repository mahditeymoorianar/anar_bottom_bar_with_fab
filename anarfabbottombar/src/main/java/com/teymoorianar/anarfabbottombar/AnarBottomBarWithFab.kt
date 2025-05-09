package com.teymoorianar.anarfabbottombar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.solver.state.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.teymoorianar.anarfabbottombar.AnarMenuIcon
import com.teymoorianar.anarfabbottombar.AnarMenuItem

/**
 * @author Mahdi Teymoori Anar
 * @see
 * <p>
 * <a href="https://github.com/mahditeymoorianar/anar_bottom_bar_with_fab">
 *     see in the github
 *     </a>
 *     </p>
 *
 *     <p>
 *     Licensed under GNU LGPL
 *     </p>
 */

class AnarBottomBarWithFab @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    // views
    private lateinit var fab: FloatingActionButton
    private lateinit var startImageView: ImageView
    private lateinit var middleImageView: ImageView
    private lateinit var endImageView: ImageView
    private lateinit var mainImageView: ImageView
    private lateinit var menuIconsContainerStart: LinearLayout
    private lateinit var menuIconsContainerEnd: LinearLayout
    // attributes
    private lateinit var bgColor: ColorStateList
    private lateinit var fabColor: ColorStateList
    private lateinit var iconColorNotSelected: ColorStateList
    private lateinit var iconColorSelected: ColorStateList
    private var activationBarThickness: Int = (context.resources.displayMetrics.density * 5).toInt()
    private var showActivationBar: Boolean = true
    private var showName: Boolean = true
    private var fontFamily: Typeface? = null
    // menus
    private var menuItems: ArrayList<AnarMenuItem> = ArrayList()


    fun setBgColor(value: Int) {
        setBgColor(ColorStateList.valueOf(value))
    }

    fun setBgColor(value: ColorStateList) {
        bgColor = value
        startImageView.backgroundTintList = bgColor
        mainImageView.backgroundTintList = bgColor
        endImageView.backgroundTintList = bgColor
    }

    fun setFabColor(value: Int) {
        setFabColor(ColorStateList.valueOf(value))
    }

    fun setFabColor(value: ColorStateList) {
        fabColor = value
        fab.backgroundTintList = fabColor
    }

    fun setIconColorNotSelected(value: Int) {
        setIconColorNotSelected(ColorStateList.valueOf(value))
    }

    fun setIconColorNotSelected(value: ColorStateList) {
        iconColorNotSelected = value
    }

    fun setIconColorSelected(value: Int) {
        setIconColorSelected(ColorStateList.valueOf(value))
    }

    fun setIconColorSelected(value: ColorStateList) {
        iconColorSelected = value
    }

    fun getIconColorSelected(): ColorStateList = iconColorSelected
    fun getIconColorNotSelected(): ColorStateList = iconColorNotSelected

    fun addMenuItem(menuItem: AnarMenuItem) {
        menuItems.add(menuItem)
        renderMenuItems()
    }

    private fun renderMenuItems() {
        menuIconsContainerStart.removeAllViews()
        menuIconsContainerEnd.removeAllViews()
        if (menuItems.size == 0) return
        val layoutParams: LinearLayout.LayoutParams
            = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT,
                1F)
        val allIcons = ArrayList<AnarMenuIcon>()
        for (i in 1..menuItems.size) {
            val menuItem = menuItems.get(i-1)
            val menuIcon: AnarMenuIcon = AnarMenuIcon(context).apply {
                setInfo(menuItem)
            }
            menuIcon.setLayoutParams(layoutParams)
            val activationBar = menuIcon.findViewById<ImageView>(R.id.activationBar)
            menuIcon.showActivationBar = showActivationBar
            activationBar.layoutParams.height = activationBarThickness

            val nameTextView: TextView = menuIcon.menuNameView
            if (showName) {
                nameTextView.visibility = VISIBLE
            } else {
                nameTextView.visibility = GONE
            }
            menuIcon.menuNameView.setTypeface(fontFamily)
            allIcons.add(menuIcon)
            if (i <= (menuItems.size+1)/2) {
                menuIconsContainerStart.addView(menuIcon)
            } else {
                menuIconsContainerEnd.addView(menuIcon)
            }
        }

        for (i in allIcons) {
            i.group = allIcons
            i.activeColor = getIconColorSelected()
            i.inactiveColor = getIconColorNotSelected()
        }
        allIcons[0].setActivation(true)
    }

    init {
        // Inflate the layout if using an XML layout file
        LayoutInflater.from(context).inflate(R.layout.layout_anar_bottom_bar_with_fab, this, true)

        // Retrieve custom attributes
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AnarBottomBarWithFab,
            defStyleAttr,
            0
        ).apply {
            try {
                // Get the background color attribute; default to transparent if not set
//                val bgColor = getColor(R.styleable.AnarBottomBarWithFab, Color.TRANSPARENT)
//                setBackgroundColor(bgColor)
                fab = findViewById(R.id.floatingActionButton)
                startImageView = findViewById(R.id.imageViewStart)
                middleImageView = findViewById(R.id.imageViewMiddle)
                endImageView = findViewById(R.id.imageViewEnd)
                mainImageView = findViewById(R.id.imageViewMain)
                menuIconsContainerStart = findViewById(R.id.menuIconsStart)
                menuIconsContainerEnd = findViewById(R.id.menuIconsEnd)

                val bgColorInt = getColor(R.styleable.AnarBottomBarWithFab_myBgColor, Color.argb(255, 90, 24,152))
                val fabColorInt = getColor(R.styleable.AnarBottomBarWithFab_fabColor, bgColorInt)
                val fabIconSize = getDimension(R.styleable.AnarBottomBarWithFab_fabIconSize, 120f)
                val fabIcon = getDrawable(R.styleable.AnarBottomBarWithFab_fabIcon)
                activationBarThickness = getDimension(R.styleable.AnarBottomBarWithFab_activationBarThickness,
                    5f).toInt()
                showActivationBar = getBoolean(R.styleable.AnarBottomBarWithFab_showActivationBar, true)
                showName = getBoolean(R.styleable.AnarBottomBarWithFab_showName, true)

                val typedArray = context.obtainStyledAttributes(attrs, intArrayOf(android.R.attr.fontFamily))
                try {
                    val fontFamilyResourceId = typedArray.getResourceId(0, 0)
                    if (fontFamilyResourceId != 0) {
                        fontFamily = ResourcesCompat.getFont(context, fontFamilyResourceId)
                    } else {
                        val fontFamilyName = typedArray.getString(0)
                        if (fontFamilyName != null) {
                            fontFamily = Typeface.create(fontFamilyName, Typeface.NORMAL)
                        }
                    }
                } finally {
                    typedArray.recycle()
                }

                iconColorSelected = ColorStateList.valueOf(getColor(R.styleable.AnarBottomBarWithFab_iconColorSelected,Color.WHITE))
                iconColorNotSelected = ColorStateList.valueOf(getColor(R.styleable.AnarBottomBarWithFab_iconColorNotSelected,  Color.GRAY))
                Log.d("fab", "fabColor: $fabColorInt")
//                fab.setImageResource(fabIcon)
//                fab.setImageResource(getInteger(R.styleable.AnarBottomBarWithFab_fabIcon, R.drawable.ic_round_add_24))
                fab.setImageDrawable(
                    getDrawable(R.styleable.AnarBottomBarWithFab_fabIcon) ?:
                    ContextCompat.getDrawable(context, R.drawable.ic_round_add_24)
                )
                fab.setMaxImageSize(fabIconSize.toInt())

                setBgColor(bgColorInt)
                setFabColor(fabColorInt)
                renderMenuItems()

            } finally {
                recycle()
            }
        }
    }

    fun setFabOnClickListener(listener: OnClickListener) {
        fab.setOnClickListener(listener)
    }
}

