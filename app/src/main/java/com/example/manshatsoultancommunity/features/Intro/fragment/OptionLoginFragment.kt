package com.example.manshatsoultancommunity.features.Intro.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentLoginOptionBinding
import com.example.manshatsoultancommunity.features.Intro.data.model.Admin
import com.example.manshatsoultancommunity.features.news.presentation.common.NewsActivity
import com.example.manshatsoultancommunity.utils.Constants.ADMIN_COLLECTION
import com.example.manshatsoultancommunity.utils.Constants.CODE_AUTH_KEY
import com.example.manshatsoultancommunity.utils.TransitionListener
import com.example.manshatsoultancommunity.utils.dailogs.setupButtonSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
const val TAG = "OptionLoginFragment"
@AndroidEntryPoint
class OptionLoginFragment: Fragment(){
    lateinit var firestore : FirebaseFirestore
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
            Intent(requireActivity(),NewsActivity::class.java).also { intent ->
                startActivity(intent)
            }
        }

        firestore = Firebase.firestore
        firestore.collection(ADMIN_COLLECTION).document("T-132605").get()
            .addOnSuccessListener { result ->
                if (result.exists()){
                    val admin = result.toObject(Admin::class.java)

                }else {

                }

            }.addOnFailureListener {

            }

        val nameOfAdmin = "فتحي سرحان"
        val sheetCodeAdminAuth = listOf<String>("F-W2365","A-W2005","A-W1395","A-W1113")
        binding.buttonAdminAccountOptions.setOnClickListener {
            showButtonSheetDialog(nameOfAdmin,sheetCodeAdminAuth)
        }
    }
    private fun showSnackBarMessage(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        configurationSnackBar(snackbar)
        snackbar.show()
    }
    private fun showButtonSheetDialog(name:String?, sheetCodeList :List<String>) {
        setupButtonSheetDialog { code ->
            sheetCodeList.forEach {sheetCodeAuth ->
                if (code == sheetCodeAuth) {
                    showSnackBarMessage("${getString(R.string.welcom_message_admin)} $name")
                    Intent(requireActivity(), NewsActivity::class.java).also { intent ->
                        intent.putExtra(CODE_AUTH_KEY,sheetCodeAuth)
                        startActivity(intent)
                        applyTransition()
                    }
                } else {
                    showSnackBarMessageWithAction(name,sheetCodeList)
                }
            }
        }
    }
    private fun showSnackBarMessageWithAction(name: String?, sheetCodeList :List<String>) {
        val snackbar = Snackbar.make(binding.root, getString(R.string.wrong_code), Snackbar.LENGTH_LONG)
        snackbar.setAction(getString(R.string.try_again_action)) {
            showButtonSheetDialog(name,sheetCodeList)
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