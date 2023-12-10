package com.example.manshatsoultancommunity.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.example.manshatsoultancommunity.R
import de.hdodenhof.circleimageview.CircleImageView

class CustomGradientCircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CircleImageView(context, attrs, defStyleAttr) {

    init {
        // Create a GradientDrawable
        val gradientDrawable = GradientDrawable()

        // Set gradient colors (replace these with your desired colors)
        val startColor = ContextCompat.getColor(context, R.color.title)
        val endColor = ContextCompat.getColor(context, R.color.white)
        gradientDrawable.colors = intArrayOf(startColor, endColor)

        // Set gradient type (linear, radial, etc.)
        gradientDrawable.gradientType = GradientDrawable.LINEAR_GRADIENT

        // Set gradient orientation (left to right in this case)
        gradientDrawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT

        // Set corner radius to make it circular
        gradientDrawable.cornerRadius = width / 2f

        // Set the drawable as the background
        background = gradientDrawable
    }
}