package com.example.manshatsoultancommunity.features.Intro.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentLoginOptionBinding
import com.example.manshatsoultancommunity.features.Intro.data.model.Admin
import com.example.manshatsoultancommunity.features.news.presentation.common.activity.NewsActivity
import com.example.manshatsoultancommunity.util.Constants.ADMIN_COLLECTION
import com.example.manshatsoultancommunity.util.Constants.Auth_STATUS
import com.example.manshatsoultancommunity.util.SharedPreferencesManager
import com.example.manshatsoultancommunity.util.TransitionListener
import com.example.manshatsoultancommunity.util.dailogs.setupButtonSheetDialog
import com.example.manshatsoultancommunity.util.saveAdminData
import com.example.manshatsoultancommunity.util.showToast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class OptionLoginFragment: Fragment(){
    private lateinit var firestore : FirebaseFirestore
    private lateinit var binding : FragmentLoginOptionBinding
    private var transitionListener: TransitionListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginOptionBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TransitionListener) {
            //checks if the context (which is the hosting Activity of the Fragment)
            // implements the TransitionListener interface
            transitionListener = context
        } else {
            throw RuntimeException("$context must implement TransitionListener")
        }
    }
    override fun onDetach() {
        super.onDetach()
        transitionListener = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUserAccountOptions.setOnClickListener {
            //applyTransition()
            SharedPreferencesManager(requireContext()).saveString(Auth_STATUS,"User")
            Intent(requireActivity(), NewsActivity::class.java).also { intent ->
                startActivity(intent)
            }
        }
        firestore = Firebase.firestore
        binding.buttonAdminAccountOptions.setOnClickListener {
//            binding.buttonAdminAccountOptions.startAnimation()
            showButtonSheetDialog()
        }
    }
    private fun showButtonSheetDialog() {
        setupButtonSheetDialog(binding.buttonAdminAccountOptions){ codeEntry ->
            firestore.collection(ADMIN_COLLECTION).document(codeEntry).get()
                .addOnSuccessListener { result ->
                    binding.buttonAdminAccountOptions.revertAnimation()
                    if (result.exists()){
                        val admin = result.toObject(Admin::class.java)
                        saveAdminData(admin)
                        showToast("${getString(R.string.welcom_message_admin)} ${admin?.name}",R.font.btn_font_cairo_black)
                        //applyTransition()
                        Intent(requireActivity(), NewsActivity::class.java).also { intent ->
                            startActivity(intent)
                        }

                    }else {
                        showSnackBarMessageWithAction()
                    }
                }.addOnFailureListener {
                    showToast("لا يوجد اتصال بالانترنت")
                    binding.buttonAdminAccountOptions.revertAnimation()
                }
        }
    }



    private fun showSnackBarMessage(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        configurationSnackBar(snackbar)
        snackbar.show()
    }

    private fun showSnackBarMessageWithAction() {
        val snackbar = Snackbar.make(binding.root, getString(R.string.wrong_code), Snackbar.LENGTH_LONG)
        snackbar.setAction(getString(R.string.try_again_action)) {
            showButtonSheetDialog()
        }
        configurationSnackBar(snackbar)
        snackbar.show()
    }
    private fun configurationSnackBar(snackbar: Snackbar) {
        snackbar.view.layoutDirection = View.LAYOUT_DIRECTION_RTL
        val textView =
            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        val actionTextView =
            snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action)
        val customFont = ResourcesCompat.getFont(requireContext(), R.font.btn_font_cairo_black)
        actionTextView.typeface = customFont
        textView.typeface = customFont
    }
    private fun applyTransition() {
        transitionListener?.applyTransition()
    }

}