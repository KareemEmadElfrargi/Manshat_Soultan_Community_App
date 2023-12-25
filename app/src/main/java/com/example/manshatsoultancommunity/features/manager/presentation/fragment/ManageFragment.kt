package com.example.manshatsoultancommunity.features.manager.presentation.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.manshatsoultancommunity.databinding.FragmentManageBinding
import com.example.manshatsoultancommunity.features.Intro.common.IntroActivity
import com.example.manshatsoultancommunity.features.Intro.data.model.Admin
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.features.news.data.model.Post
import com.example.manshatsoultancommunity.util.Constants.ADMIN_COLLECTION
import com.example.manshatsoultancommunity.util.Constants.Auth_STATUS
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_EDUCATION_POST
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_GENERAL_POST
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_RIP_POST
import com.example.manshatsoultancommunity.util.Constants.CATEGORY_TYPE_SPORT_POST
import com.example.manshatsoultancommunity.util.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.util.Constants.CHILD_OF_POST_REALTIME
import com.example.manshatsoultancommunity.util.SharedPreferencesManager
import com.example.manshatsoultancommunity.util.clearAdminData
import com.example.manshatsoultancommunity.util.generateUniqueId
import com.example.manshatsoultancommunity.util.getAdminData
import com.example.manshatsoultancommunity.util.getCurrentTime
import com.example.manshatsoultancommunity.util.saveAdminData
import com.example.manshatsoultancommunity.util.showAlertDialog
import com.example.manshatsoultancommunity.util.showToast
import com.example.manshatsoultancommunity.util.visibilityGone
import com.example.manshatsoultancommunity.util.visibilityInVisible
import com.example.manshatsoultancommunity.util.visibilityVisible
import com.github.dhaval2404.imagepicker.ImagePicker

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ManageFragment: Fragment() {
//    private val args by navArgs<ManageFragmentArgs>()
    private lateinit var binding : FragmentManageBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private  var categoryType : String?=null
    private  var nameOfChannel : String?=null
    private  var imageOfProfile : String?=null

    private val storageReference: StorageReference by lazy {
        FirebaseStorage.getInstance().reference
    }
    private var isPin = false
    private lateinit var authStatus : String
    private  var uriFormGallery : Uri? = null
    private lateinit var downloadUriAdvertisment : String
    private  var downloadUriPosts : String? =null

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
        //val post = args.post!!.postId

        firebaseDatabase = FirebaseDatabase.getInstance()

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
                blockingAnimation.visibilityGone()
            }
        }else if (authStatus == "Admin") {
            updateAdminData()
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

        binding.radioGroupForPost.setOnCheckedChangeListener{ _ , checkId ->
            when(checkId){
                binding.rdGeneralNews.id ->{
                    categoryType = CATEGORY_TYPE_GENERAL_POST
                    nameOfChannel = "منشاة سلطان"
                    imageOfProfile = "https://firebasestorage.googleapis.com/v0/b/manshat-soultan-community.appspot.com/o/410850549_3543666529283645_4261973851753068178_n.jpg?alt=media&token=556675c0-0a92-4395-9277-675458a70f62"
                }
                binding.rdRipNews.id ->{
                    categoryType = CATEGORY_TYPE_RIP_POST
                    nameOfChannel = "وفيات م.سلطان"
                    imageOfProfile = "https://firebasestorage.googleapis.com/v0/b/manshat-soultan-community.appspot.com/o/images%2FOther%2Frip_image.jpg?alt=media&token=89a178dc-5a1a-4003-a887-7cdf3b8d6b38"
                }
                binding.rdSportNews.id ->{
                    categoryType = CATEGORY_TYPE_SPORT_POST
                    nameOfChannel = "م. شباب م.سلطان"
                    imageOfProfile = "https://firebasestorage.googleapis.com/v0/b/manshat-soultan-community.appspot.com/o/sport_image_profile.jpg?alt=media&token=7c8db50f-e793-419b-863d-1822bd046f16"
                }
                binding.rdEducationNews.id ->{
                    categoryType = CATEGORY_TYPE_EDUCATION_POST
                    nameOfChannel = "م.الشهيد شعبان"
                    imageOfProfile = "https://firebasestorage.googleapis.com/v0/b/manshat-soultan-community.appspot.com/o/img_shcool_profile.jpg?alt=media&token=25f28de7-e85c-4172-97d5-535295c0101a"
                }
            }
        }

        binding.uploadImgPost.setOnClickListener {
            ImagePicker.with(this@ManageFragment)
                .crop()
                .compress(1024)
                .start()
        }

        binding.publishPost.setOnClickListener {
            binding.publishPost.startAnimation()
            uploadDataForPosts()
        }

    }

    private fun uploadDataForPosts(){
        val referencePostsImage = storageReference.child("images/Posts/${generateUniqueId()}.jpg")
        val contentPost = binding.contentPost.text.toString()
        if (contentPost.isNotBlank() && !categoryType.isNullOrBlank()) {
            if (uriFormGallery !=null){
                val uploadTask = referencePostsImage.putFile(uriFormGallery!!)
                uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let { throw it }
                    }

                    referencePostsImage.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        downloadUriPosts = task.result.toString()
                        uploadPost()
                    }
                }.toString()
            }else{
                uploadPost()
            }

        }
        else {
            showToast("يجب عليك ادخال جميع بيانات")
            binding.publishPost.revertAnimation()
        }
    }

    private fun uploadPost() {
        val contentPost = binding.contentPost.text.toString()
        val postId = generateUniqueId()
        val post  = Post(postId,
            categoryType,
            nameOfChannel,
            imageOfProfile,
            downloadUriPosts,
            getCurrentTime(),
            getAdminData().name,
            contentPost,
            false,
            getAdminData().rating
        )

        firebaseDatabase.reference.child(CHILD_OF_POST_REALTIME).child(postId).setValue(post)
            .addOnSuccessListener {
                binding.publishPost.revertAnimation()
                showToast("تم رفع المنشور بنجاح")
            }.addOnFailureListener {
                binding.publishPost.revertAnimation()
                showToast("لم يتم رفع المنشور ")
            }

        cleanFiledForPost()
    }

    private fun cleanFiledForPost() {
        binding.contentPost.text = null
        binding.cardImage.visibilityGone()
        binding.showImagePost.setImageURI(null)
    }


    private fun updateAdminAvailability(admin:Admin){
        if (admin.active) {
            binding.nameOfManager.text = " مرحباً بك ${admin.name}"

            admin.category.forEach { category ->
                when (category) {
                    "Sport", "Rip", "Education", "General" -> {
                        binding.apply {
                            parentViewManagers.visibilityVisible()
                            parentViewUsers.visibilityGone()
                            parentViewOwnerAds.visibilityGone()
                            blockingAnimation.visibilityGone()

                            if (category == "Sport"){
                                rdSportNews.visibilityVisible()
                            }
                            if (category == "Rip"){
                                rdRipNews.visibilityVisible()
                            }
                            if (category == "Education"){
                                rdEducationNews.visibilityVisible()
                            }
                            if (category == "General"){
                                rdGeneralNews.visibilityVisible()
                            }


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

    private fun updateAdminData(){
        val docRef = FirebaseFirestore.getInstance().collection(ADMIN_COLLECTION).document(getAdminData().id)
        docRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                // Handle the error
                return@addSnapshotListener
            }
            // Check if the document exists and has data
            if (snapshot != null && snapshot.exists()) {
                val admin = snapshot.toObject(Admin::class.java)
                saveAdminData(admin)
                updateAdminAvailability(admin!!)
            } else {
                // The document doesn't exist
            }
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
                    downloadUriAdvertisment = task.result.toString()
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
            downloadUriAdvertisment,
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

                val cashedCategory = getAdminData().category
                cashedCategory.forEach{ category ->
                    if (category == CATEGORY_TYPE_RIP_POST
                        || category == CATEGORY_TYPE_SPORT_POST
                        || category == CATEGORY_TYPE_GENERAL_POST
                        ||  category == CATEGORY_TYPE_EDUCATION_POST){
                        binding.apply {
                            cardImage.visibilityVisible()
                            showImagePost.setImageURI(uriFormGallery)
                        }
                    } else if (category=="Advertisement") {
                        binding.addImageAds.setImageURI(uriFormGallery)
                        binding.iconOfImageAds.visibilityInVisible()
                    }
                }

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