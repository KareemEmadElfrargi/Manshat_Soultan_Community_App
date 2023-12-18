package com.example.manshatsoultancommunity.util.dailogs

import android.annotation.SuppressLint
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.util.showToast
import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

@SuppressLint("MissingInflatedId")
fun Fragment.setupButtonSheetDialog(
    buttonAnmation:CircularProgressButton,
    onConfirmClick:(String)->Unit,

){
    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStylle)
    val view = layoutInflater.inflate(R.layout.admin_code_dialog,null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val edCode = view.findViewById<EditText>(R.id.edAdminCodeEntry)
    val btnConfirm = view.findViewById<CircularProgressButton>(R.id.buttonConfirmAdminCode)
    val btnCancel = view.findViewById<CircularProgressButton>(R.id.buttonCancelAdminCode)
    btnConfirm.setOnClickListener {
        buttonAnmation.startAnimation()
        val code = edCode.text.toString().trim()
        if (code.isNotBlank()){
            onConfirmClick(code)
            dialog.dismiss()
        }else{
            showToast("أدخل الكود")
        }
    }
    btnCancel.setOnClickListener{
        dialog.dismiss()
        buttonAnmation.revertAnimation()
    }
}