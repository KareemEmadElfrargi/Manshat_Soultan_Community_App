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
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.util.Constants.Auth_STATUS
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_SPORT_POST
import com.example.manshatsoultancommunity.util.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.util.SharedPreferencesManager
import com.example.manshatsoultancommunity.util.clearAdminData
import com.example.manshatsoultancommunity.util.generateUniqueId
import com.example.manshatsoultancommunity.util.getAdminData
import com.example.manshatsoultancommunity.util.getCurrentTime
import com.example.manshatsoultancommunity.util.showAlertDialog
import com.example.manshatsoultancommunity.util.showToast
import com.example.manshatsoultancommunity.util.visibilityGone
import com.example.manshatsoultancommunity.util.visibilityInVisible
import com.example.manshatsoultancommunity.util.visibilityVisible
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

        val post = Post(
            CATEGORY_TYPE_SPORT_POST,
            "وفيات قرية منشاة سلطان",
            "https://firebasestorage.googleapis.com/v0/b/manshat-soultan-community.appspot.com/o/images%2FOther%2Frip_image.jpg?alt=media&token=89a178dc-5a1a-4003-a887-7cdf3b8d6b38",
            null,
            getCurrentTime(),
            getAdminData().name,
            "توفي الي رحمة الله تعالي الحاج علي محمد علي والدفنة عند حضور الجثة من مسجد الرحمة",
            true,
            getAdminData().rating)

        //firebaseDatabase.reference.child(CHILD_OF_POST_REALTIME).push().setValue(post)


        admin = getAdminData()
        authStatus = SharedPreferencesManager(requireContext()).getString(Auth_STATUS)


        binding.logoutManagerBtn.setOnClickListener {
            showAlertDialog("تسجيل الخروج","لتسجيل الخروج من التطبيق اضفط زر تسجيل الخروج",{
                SharedPreferencesManager(requireContext()).apply {
                    clearItem(Auth_STATUS)
                    clearAdminData()
                }
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
                                blockingAnimation.visibilityGone()
                            }
                        }

                        "Advertisement" -> {
                            binding.apply {
                                parentViewManagers.visibilityGone()
                                parentViewUsers.visibilityGone()
                                parentViewOwnerAds.visibilityVisible()
                                blockingAnimation.visibilityGone()
                            }
                        }
                    }
                }
            } else {
                binding.apply {
                    parentViewManagers.visibilityGone()
                    parentViewUsers.visibilityGone()
                    parentViewOwnerAds.visibilityGone()
                    blockingAnimation.visibilityVisible()
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
            iconOfImageAds.visibilityInVisible()
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
                showToast("لم يتم تحديد صورة")
            }
        }
    }

}