package com.github.kostasdrakonakis

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.github.kostasdrakonakis.buttonpreference.R

class ButtonPreference : Preference {
    private var mGravity = 0
    private var mColor = 0
    private var mPadding = 0
    private var mDrawable = 0
    private var mButtonBackgroundColor = 0
    private var mVisibility = 0
    private var mAllCaps = false
    private lateinit var mClickListener: () -> Unit

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        val itemView = holder.itemView
        val container: LinearLayout? = itemView as LinearLayout?
        container?.setBackgroundColor(mColor)
        container?.setPadding(mPadding, mPadding, mPadding, mPadding)
        val button = itemView.findViewById(R.id.button_preference) as Button?
        if (mDrawable != 0) {
            button?.setBackgroundResource(mDrawable)
        }
        if (mButtonBackgroundColor != 0) {
            button?.setBackgroundColor(mButtonBackgroundColor)
        }
        button?.text = title
        button?.isAllCaps = mAllCaps
        button?.visibility = mVisibility
        setButtonGravity(button)
        button?.setOnClickListener { _ ->
            mClickListener()
        }
    }

    fun setOnClickListener(listener: () -> Unit) {
        mClickListener = listener
    }

    fun setButtonBackgroundColor(@ColorRes color: Int) {
        mButtonBackgroundColor = ContextCompat.getColor(context, color)
    }

    fun setVisibility(visibility: Int) {
        mVisibility = visibility
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        this.layoutResource = R.layout.preference_button
        if (attrs == null) return
        val array = context.obtainStyledAttributes(attrs, R.styleable.ButtonPreference)
        val color = array.getColor(R.styleable.ButtonPreference_preferenceLayoutColor, Color.WHITE)
        val padding = array.getDimensionPixelOffset(R.styleable.ButtonPreference_preferenceLayoutPadding,
            context.resources.getDimensionPixelOffset(
                R.dimen.preference_button_container_default_padding))
        val drawable = array.getResourceId(R.styleable.ButtonPreference_preferenceBackground, 0)
        val buttonBackgroundColor = array.getColor(R.styleable.ButtonPreference_preferenceBackgroundColor, 0)
        mAllCaps = array.getBoolean(R.styleable.ButtonPreference_preferenceAllCaps, true)
        mVisibility = if (array.getBoolean(R.styleable.ButtonPreference_preferenceVisible, true)) View.VISIBLE else View.GONE
        if (color != 0) {
            mColor = color
        }
        if (padding != 0) {
            mPadding = padding
        }
        if (drawable != 0) {
            mDrawable = drawable
        }
        if (buttonBackgroundColor != 0) {
            mButtonBackgroundColor = buttonBackgroundColor
        }
        array.recycle()
        mGravity = attrs.getAttributeIntValue(NAMESPACE, "layout_gravity", Gravity.START)
    }

    private fun setButtonGravity(button: Button?) {
        val linearLayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        linearLayoutParams.gravity = mGravity
        button?.layoutParams = linearLayoutParams
    }

    companion object {
        private const val NAMESPACE = "http://schemas.android.com/apk/res/android"
    }
}