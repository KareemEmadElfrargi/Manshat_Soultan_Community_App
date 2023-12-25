package com.example.manshatsoultancommunity.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.PorterDuff
import android.media.ExifInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.features.Intro.data.model.Admin
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID
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
fun Fragment.showToastStyle(message: Any){
    val inflater = layoutInflater
    val layout = inflater.inflate(R.layout.custom_toast_layout, null)

    val textView = layout.findViewById<TextView>(R.id.textViewToast)
    textView.text = message.toString()

    with(Toast(context)) {
        setGravity(Gravity.BOTTOM, 0, 150)
        duration = Toast.LENGTH_SHORT
        view = layout
        show()
    }
}
fun Fragment.showToastStyleWithPopUpMenu(message: Any,menuItem:MenuItem){
    val inflater = layoutInflater
    var layout: View? = null
    when(menuItem.itemId){
        R.id.delete_post ->{
            layout = inflater.inflate(R.layout.custom_toast_delete_layout, null)
        }
        R.id.update_post ->{
            layout = inflater.inflate(R.layout.custom_toast_edit_layout, null)
        }
    }
    val textView = layout?.findViewById<TextView>(R.id.textViewToast)
    textView?.text = message.toString()

    with(Toast(context)) {
        setGravity(Gravity.BOTTOM, 0, 150)
        duration = Toast.LENGTH_SHORT
        view = layout
        show()
    }
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
 fun getCurrentTime(): String {
    val currentTime = Calendar.getInstance().time
    val timeFormat = SimpleDateFormat("EEE  hh:mm a , d MMM ", Locale("ar"))
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

 fun Fragment.contactByWhatsApp(phoneNumber: String, codeCountry:String) {

    if (!isWhatsAppInstalled()){
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$codeCountry$phoneNumber")
        val whatsappIntent = Intent(Intent.ACTION_VIEW,uri)
        startActivity(whatsappIntent)
    }else{
        contactByCall(phoneNumber)
        showToast("مفيش واتس اب علي تليفونك")
    }
}

 fun Fragment.contactByCall(phoneNumber: String) {
    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
    startActivity(dialIntent)
}

// TODO:WhatsAPP Config
 fun Fragment.isWhatsAppInstalled(): Boolean {
    val pm  =  requireActivity().packageManager
    val whatsappPackageNames = listOf("com.whatsapp", "com.whatsapp.w4b")
    for (packageName in whatsappPackageNames) {
        return try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
    return false
}

fun generateUniqueId(): String {
    return UUID.randomUUID().toString()
}

 fun Fragment.isInternetAvailable(): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities =
        connectivityManager.getNetworkCapabilities(network)
    return capabilities != null &&
            (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities =
        connectivityManager.getNetworkCapabilities(network)

    return capabilities != null &&
            (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            //isServerReachable("www.google.com", 80, 1000) // Adjust the server and timeout as needed
}


private fun isServerReachable(serverAddress: String, port: Int, timeout: Int): Boolean {
    return try {
        val socket = Socket()
        socket.connect(InetSocketAddress(serverAddress, port), timeout)
        socket.close()
        true
    } catch (e: IOException) {
        Log.e("NetworkUtils", "Error checking server reachability: ${e.message}")
        false
    }

}

fun loadImageData(imageUrl: String?,context: Context): ByteArray {
    return try {

        if (imageUrl.isNullOrBlank()) {
            Log.e("loadImageData", "Image URL is null or empty")
            return byteArrayOf() // Default empty byte array for simplicity
        }

        // Use Glide to load the image and retrieve the byte array
        val glideRequest = Glide.with(context)
            .asBitmap()
            .load(imageUrl)

        val bitmap = glideRequest.submit().get()
        // Convert the Bitmap to a ByteArray
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.toByteArray()
    } catch (e: Exception) {
        // Handle errors, such as network issues or image ;loading failures
        // You might want to log the error or return a default image data
        e.printStackTrace()
        byteArrayOf() // Default empty byte array for simplicity
    }
}

suspend fun loadImageDataAsync(imageUrl: String?, context: Context): ByteArray {
    return withContext(Dispatchers.IO) {
        try {
            if (imageUrl.isNullOrBlank()) {
                Log.e("loadImageData", "Image URL is null or empty")
                return@withContext byteArrayOf() // Default empty byte array for simplicity
            }

            // Use Glide to load the image and retrieve the byte array
            val glideRequest = Glide.with(context)
                .asBitmap()
                .load(imageUrl)

            val bitmap: Bitmap = glideRequest.submit().get()

            // Convert the Bitmap to a ByteArray
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.toByteArray()
        } catch (e: Exception) {
            // Handle errors, such as network issues or image loading failures
            // You might want to log the error or return a default image data
            e.printStackTrace()
            return@withContext byteArrayOf() // Default empty byte array for simplicity
        }
    }
}





fun Fragment.getAdminData(): Admin {
    val admin : Admin
    SharedPreferencesManager(requireContext()).apply {
        val id = getString(Constants.ADMIN_ID_KEY)
        val name = getString(Constants.ADMIN_NAME_KEY)
        val category = getList(Constants.ADMIN_CATEGORY_KEY)
        val active = getBoolean(Constants.ADMIN_ACTIVE_KEY)
        val rate = getInt(Constants.ADMIN_RATE_KEY)
        admin = Admin(id,name,category,active,rate)
    }
    return admin
}

 fun Fragment.saveAdminData(admin: Admin?) {
    SharedPreferencesManager(requireContext()).apply {
        admin?.let {
            saveString(Constants.ADMIN_ID_KEY,admin.id)
            saveString(Constants.ADMIN_NAME_KEY, admin.name)
            saveList(Constants.ADMIN_CATEGORY_KEY, admin.category)
            saveBoolean(Constants.ADMIN_ACTIVE_KEY,admin.active)
            saveString(Constants.Auth_STATUS,"Admin")
            saveInt(Constants.ADMIN_RATE_KEY,admin.rating)
        }
    }
}

fun Fragment.clearAdminData() {
    SharedPreferencesManager(requireContext()).apply {
        clearItem(Constants.ADMIN_ID_KEY)
        clearItem(Constants.ADMIN_NAME_KEY)
        clearItem(Constants.ADMIN_CATEGORY_KEY)
        clearItem(Constants.ADMIN_ACTIVE_KEY)
        clearItem(Constants.Auth_STATUS)
        clearItem(Constants.ADMIN_RATE_KEY)
    }
}

fun Fragment.showAlertDialog(title:String,
                             message:String,
                             positive: () -> Unit,
                             negativie:(dialog:DialogInterface) -> Unit){

    val inflater = requireActivity().layoutInflater
    val view = inflater.inflate(R.layout.custom_alert_dialog_layout, null)

    val titleTextView: TextView = view.findViewById(R.id.alertDialogTitle)
    val messageTextView: TextView = view.findViewById(R.id.alertDialogMessage)
    val positiveButton: Button = view.findViewById(R.id.alertDialogPositiveButton)
    val negativeButton: Button = view.findViewById(R.id.alertDialogNegativeButton)

    titleTextView.text = title
    messageTextView.text = message

    val alertDialog = AlertDialog.Builder(requireContext())
        .setView(view)
        .create()

    alertDialog.show()
    positiveButton.setOnClickListener { positive() }
    negativeButton.setOnClickListener { negativie(alertDialog) }
}


fun <T> Fragment.showPopupMenu(post: T,
                               view:View,
                               delete:(post:T,menuItem:MenuItem)->Unit,
                               update:(post:T,menuItem:MenuItem)->Unit,
                               onDismiss: () -> Unit) {
    val popupMenu = PopupMenu(requireContext(),view,0,0,R.style.PopupMenuStyle)
    popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

    for (i in 0 until popupMenu.menu.size()) {
        val menuItem = popupMenu.menu.getItem(i)
        val spannableString = SpannableString(menuItem.title)
        spannableString.setSpan(
            TextAppearanceSpan(requireContext(), R.style.PopupMenuStyleItemText),
            0,
            spannableString.length,
            0
        )
        menuItem.title = spannableString
    }

    popupMenu.setOnMenuItemClickListener { menuItem ->
        when (menuItem.itemId) {
            R.id.delete_post -> {
                delete(post,menuItem)
                true
            }
            R.id.update_post -> {
                update(post,menuItem)
                true
            }
            else -> false
        }
    }
    popupMenu.setOnDismissListener {
        onDismiss()
    }
    popupMenu.show()


    try {
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popupMenu)
        menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
            .invoke(menu, true)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun ImageView.changeColorOfVectorIcon(context:Context, iconRes:Int, colorRes:Int) {
    val drawable = ContextCompat.getDrawable(context, iconRes)
    val color = ContextCompat.getColor(context,colorRes)
    DrawableCompat.setTint(drawable!!, color)
    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
    setImageDrawable(drawable)
}



        // When the user scrolls, it checks if the bottom of the content , presumably to load more data as the user scrolls.
//        binding.nestedScrollAdsPage.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{ view, _, scrollY, _, _ ->
//
//            val canScrollHorizontally = view.canScrollHorizontally(1) || view.canScrollHorizontally(-1)
//            val canScrollVertically = view.canScrollVertically(1) || view.canScrollVertically(-1)
//
//            if (canScrollHorizontally || canScrollVertically) {
//                // view: This represents the NestedScrollView instance on which the scroll change listener is set
//                if (view.getChildAt(0).bottom <= view.height + scrollY) {
//                    viewModel.fetchAdsPosts()
//                }
//            }
//        })
