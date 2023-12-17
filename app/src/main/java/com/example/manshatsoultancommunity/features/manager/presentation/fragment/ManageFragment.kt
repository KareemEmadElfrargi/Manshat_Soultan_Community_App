package com.example.manshatsoultancommunity.features.manager.presentation.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentManageBinding
import com.example.manshatsoultancommunity.features.Intro.common.IntroActivity
import com.example.manshatsoultancommunity.features.Intro.data.model.Admin
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.utils.Constants.Auth_STATUS
import com.example.manshatsoultancommunity.utils.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.utils.SharedPreferencesManager
import com.example.manshatsoultancommunity.utils.generateUniqueId
import com.example.manshatsoultancommunity.utils.getAdminData
import com.example.manshatsoultancommunity.utils.getCurrentTime
import com.example.manshatsoultancommunity.utils.showAlertDialog
import com.example.manshatsoultancommunity.utils.showToast
import com.example.manshatsoultancommunity.utils.visibilityGone
import com.example.manshatsoultancommunity.utils.visibilityInVisible
import com.example.manshatsoultancommunity.utils.visibilityVisible
import com.github.dhaval2404.imagepicker.ImagePicker

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ManageFragment: Fragment() {
    private lateinit var binding : FragmentManageBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private val storageReference: StorageReference by lazy {
        FirebaseStorage.getInstance().reference
    }
    private var isPin = false
    private lateinit var admin : Admin
    private lateinit var authStatus : String
    private  var uriFormGallery : Uri? = null
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
        authStatus = SharedPreferencesManager(requireContext()).getString(Auth_STATUS)


        binding.logoutManagerBtn.setOnClickListener {
            showAlertDialog("تسجيل الخروج","لتسجيل الخروج من التطبيق اضفط زر تسجيل الخروج",{
                SharedPreferencesManager(requireContext()).clearString(Auth_STATUS)
                startActivity(Intent(requireContext(), IntroActivity::class.java))
                activity?.finish()
            },{ dialog ->
                dialog.dismiss()
            })
        }

        if (authStatus=="User"){
            binding.apply {
                parentViewManagers.visibilityGone()
                parentViewUsers.visibilityVisible()
                parentViewOwnerAds.visibilityGone()
            }
        }else if (authStatus == "Admin") {
            if (admin.active) {
                admin.category.forEach { category ->
                    when (category) {
                        "Sport", "RIP", "Education", "General" -> {
                            binding.apply {
                                parentViewManagers.visibilityVisible()
                                parentViewUsers.visibilityGone()
                                parentViewOwnerAds.visibilityGone()
                                // gone for blocking page
                            }
                        }

                        "Advertisement" -> {
                            binding.apply {
                                parentViewManagers.visibilityGone()
                                parentViewUsers.visibilityGone()
                                parentViewOwnerAds.visibilityVisible()
                                // gone for blocking page
                            }
                        }
                    }
                }
            } else {
                binding.apply {
                    parentViewManagers.visibilityGone()
                    parentViewUsers.visibilityGone()
                    parentViewOwnerAds.visibilityGone()
                    //TODO : show Blocking page
                }
            }
        }


       binding.publishAdsBtn.setOnClickListener {
           binding.publishAdsBtn.startAnimation()
           uploadImageToFirebase()
       }
        binding.statusNewCheckBox.setOnCheckedChangeListener{ _,isChecked ->
            isPin = isChecked
        }

        binding.addImageAds.setOnClickListener {

            ImagePicker.with(this@ManageFragment)
                .crop()
                .compress(1024)
                .start()

        }

    }

    private fun uploadImageToFirebase() {
        val referenceAdvertisements = storageReference.child("images/Advertisements/${generateUniqueId()}.jpg")
        val titleAdvertisement = binding.titleAnnouncementManage.text.toString()
        val descriptionAdvertisement = binding.descriptionAnnouncementManage.text.toString()
        val ownerAdvertisement = binding.nameOfOwnerAnnouncementManage.text.toString()
        val datesOfWorkAdvertisement = binding.daysOfWorkAnnouncementManage.text.toString()
        val placePostedAdvertisement = binding.placeOfWorkAnnouncementManage.text.toString()
        val phoneOfAdvertisement = binding.phoneOfWorkAnnouncementManage.text.toString()

        if ( uriFormGallery != null
            && titleAdvertisement.isNotBlank()
            && descriptionAdvertisement.isNotBlank()
            && ownerAdvertisement.isNotBlank()
            && datesOfWorkAdvertisement.isNotBlank()
            && placePostedAdvertisement.isNotBlank()
            && phoneOfAdvertisement.isNotBlank()) {

            val uploadTask = referenceAdvertisements.putFile(uriFormGallery!!)
            uploadTask.continueWithTask { task ->
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
        }else {
            showToast("يجب عليك ادخال بيانات الاعلان")
            binding.publishAdsBtn.revertAnimation()
        }
    }


    private fun uploadAdsToFirebase() {

        val titleAdvertisement = binding.titleAnnouncementManage.text.toString()
        val descriptionAdvertisement = binding.descriptionAnnouncementManage.text.toString()
        val ownerAdvertisement = binding.nameOfOwnerAnnouncementManage.text.toString()
        val datesOfWorkAdvertisement = binding.daysOfWorkAnnouncementManage.text.toString()
        val placePostedAdvertisement = binding.placeOfWorkAnnouncementManage.text.toString()
        val phoneOfAdvertisement = binding.phoneOfWorkAnnouncementManage.text.toString()

        val objAnnouncement = Advertisements(
            generateUniqueId(),
            titleAdvertisement,
            downloadUri,
            descriptionAdvertisement,
            placePostedAdvertisement,
            isPin,
            getCurrentTime(),
            "اعلان ممول بواسطة $ownerAdvertisement",
            datesOfWorkAdvertisement,
            phoneOfAdvertisement
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
            addImageAds.setImageURI(null)
            statusNewCheckBox.isChecked = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                uriFormGallery = data?.data!!
                binding.addImageAds.setImageURI(uriFormGallery)
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