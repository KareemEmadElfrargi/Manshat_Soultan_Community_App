package com.example.manshatsoultancommunity.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Rect
import android.location.Geocoder
import android.media.ExifInterface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.R
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun AppCompatActivity.showToast(massage: Any) {
    Toast.makeText(this, "$massage", Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(message: Any, fontResId: Int? = null) {
    val toast = Toast.makeText(requireContext(), "$message", Toast.LENGTH_LONG)
    val toastView = toast.view

    val toastTextView = toastView?.findViewById<TextView>(android.R.id.message)
    fontResId?.let {
        val customTypeface = ResourcesCompat.getFont(requireContext(), it)
        toastTextView?.typeface = customTypeface
    }
    toastTextView?.setTextColor(ContextCompat.getColor(requireContext(), R.color.title))
    toastTextView?.gravity = Gravity.CENTER
    toast.show()
}

fun Fragment.showToastShort(massage: Any) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_SHORT).show()
}

fun View.visibilityInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.visibilityGone() {
    this.visibility = View.GONE
}

fun View.visibilityVisible() {
    this.visibility = View.VISIBLE
}

fun Fragment.showSnackBarMessage(message: String) {
    val snack = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
    snack.show()
}

fun View.disable() {
    isEnabled = false
}

fun View.enabled() {
    isEnabled = true
}

private fun getCurrentTime(): String {
    val currentTime = Calendar.getInstance().time
    val timeFormat = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
    return timeFormat.format(currentTime)}

fun Fragment.hideKeyboard() {
    val inputManager: InputMethodManager = activity
        ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    // check if no view has focus:
    val currentFocusedView = activity?.currentFocus
    if (currentFocusedView != null) {
        inputManager.hideSoftInputFromWindow(
            currentFocusedView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun Fragment.decodeFile(filePath: String?): Bitmap? {
    val o = BitmapFactory.Options()
    o.inJustDecodeBounds = true
    BitmapFactory.decodeFile(filePath, o)

// The new size we want to scale to
    val REQUIRED_SIZE = 1024

// Find the correct scale value. It should be the power of 2.
    var width_tmp = o.outWidth
    var height_tmp = o.outHeight
    var scale = 1
    while (true) {
        if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) break
        width_tmp /= 2
        height_tmp /= 2
        scale *= 2
    }

// Decode with inSampleSize
    val o2 = BitmapFactory.Options()
    o2.inSampleSize = scale
    var image = BitmapFactory.decodeFile(filePath, o2)
    val exif: ExifInterface
    try {
        exif = ExifInterface(filePath!!)
        val exifOrientation: Int = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
        var rotate = 0f
        when (exifOrientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90f
            ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180f
            ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270f
        }
        if (rotate != 0f) {
            val w = image.width
            val h = image.height

            // Setting pre rotate
            val mtx = Matrix()
            mtx.preRotate(rotate)

            // Rotating Bitmap & convert to ARGB_8888, required by tess
            image = Bitmap.createBitmap(image, 0, 0, w, h, mtx, false)
        }
    } catch (e: IOException) {
        return null
    }
    return image.copy(Bitmap.Config.ARGB_8888, true)
}

fun Fragment.showKeyboard(view: View) {
    val inputManager: InputMethodManager = activity
        ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    // check if no view has focus:
    inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}