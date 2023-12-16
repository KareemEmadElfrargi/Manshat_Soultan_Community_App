package com.example.manshatsoultancommunity.features.manager.presentation.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentManageBinding
import com.example.manshatsoultancommunity.features.Intro.data.model.Admin
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.utils.Constants
import com.example.manshatsoultancommunity.utils.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.utils.SharedPreferencesManager
import com.example.manshatsoultancommunity.utils.generateUniqueId
import com.example.manshatsoultancommunity.utils.getAdminData
import com.example.manshatsoultancommunity.utils.showToast
import com.example.manshatsoultancommunity.utils.showToastStyle
import com.example.manshatsoultancommunity.utils.visibilityGone
import com.example.manshatsoultancommunity.utils.visibilityInVisible
import com.example.manshatsoultancommunity.utils.visibilityVisible
import com.github.dhaval2404.imagepicker.ImagePicker

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.storage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ManageFragment: Fragment() {
    private lateinit var binding : FragmentManageBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private val storageReference: StorageReference by lazy {
        FirebaseStorage.getInstance().reference
    }
    private var status = false
    private lateinit var admin : Admin
    private lateinit var authStatus : String
    private lateinit var uriFormGallry : Uri
    private lateinit var downloadUri : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseDatabase = FirebaseDatabase.getInstance()


        admin = getAdminData()
        authStatus = SharedPreferencesManager(requireContext()).getString(Constants.Auth_STATUS)

        if (authStatus=="User"){
            binding.apply {
                parentViewManagers.visibilityGone()
                parentViewUsers.visibilityVisible()
                parentViewOwnerAds.visibilityGone()
            }
        }else if (authStatus == "Admin") {
            admin.category.forEach{ category ->
                when(category) {
                    "Sport" , "RIP" , "Education" -> {
                        binding.apply {
                            parentViewManagers.visibilityVisible()
                            parentViewUsers.visibilityGone()
                            parentViewOwnerAds.visibilityGone()
                        }
                    }
                    "Advertisement" ->{
                        binding.apply {
                            parentViewManagers.visibilityGone()
                            parentViewUsers.visibilityGone()
                            parentViewOwnerAds.visibilityVisible()
                        }
                    }
                }
            }
        }

       binding.publishAdsBtn.setOnClickListener {
           binding.publishAdsBtn.startAnimation()
           uploadImageToFirebase()
       }
        binding.statusNewCheckBox.setOnCheckedChangeListener{ _,isChecked ->
            status = isChecked
        }

        binding.addImageAds.setOnClickListener {

            ImagePicker.with(this@ManageFragment)
                .crop()
                .compress(1024)
                .start()

        }


    }

    private fun uploadImageToFirebase() {
        val referenceAdvertisements =
            storageReference.child("images/Advertisements/${generateUniqueId()}.jpg")
        val uploadTask = referenceAdvertisements.putFile(uriFormGallry)

        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            referenceAdvertisements.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                downloadUri = task.result.toString()
                uploadAdsToFirebase()


            } else {
                // Handle failures
                // ...
            }
        }.toString()
    }


    private fun uploadAdsToFirebase() {

        val titleAnnouncement = binding.titleAnnouncementManage.text.toString()
        val descriptionAnnouncement = binding.descriptionAnnouncementManage.text.toString()
        val owner = binding.nameOfOwnerAnnouncementManage.text.toString()
        val datesOfWork = binding.daysOfWorkAnnouncementManage.text.toString()
        val placePostedAnnouncement = binding.placeOfWorkAnnouncementManage.text.toString()
        val contactUs = binding.phoneOfWorkAnnouncementManage.text.toString()

        val objAnnouncement = Advertisements(
            generateUniqueId(),
            titleAnnouncement,
            downloadUri,
            descriptionAnnouncement,
            placePostedAnnouncement,
            status,
            "الاحد 9 صباحاً",
            "اعلان ممول بواسطة $owner",
            datesOfWork,
            contactUs
        )
        firebaseDatabase.reference.child(CHILD_OF_ADS_REALTIME).push().setValue(objAnnouncement)
            .addOnSuccessListener {
                binding.publishAdsBtn.revertAnimation()
                showToast("تم رفع الاعلان بنجاح")
        }.addOnFailureListener {
                binding.publishAdsBtn.revertAnimation()
                showToast("لم يتم رفع الاعلان")
            }
        cleanFiled()
    }

    private fun cleanFiled() {
        binding.apply {
            titleAnnouncementManage.text = null
            descriptionAnnouncementManage.text = null
            nameOfOwnerAnnouncementManage.text = null
            daysOfWorkAnnouncementManage.text  = null
            placeOfWorkAnnouncementManage.text = null
            phoneOfWorkAnnouncementManage.text = null
            statusNewCheckBox.isChecked = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                uriFormGallry = data?.data!!
                binding.addImageAds.setImageURI(uriFormGallry)
                binding.iconOfImageAds.visibilityInVisible()
            }
            ImagePicker.RESULT_ERROR -> {
                showToast(ImagePicker.getError(data))
            }
            else -> {
                showToast("Task Cancelled")
            }
        }
    }

}