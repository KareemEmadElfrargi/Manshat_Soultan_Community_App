package com.example.manshatsoultancommunity.features.manager.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.databinding.FragmentManageBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.example.manshatsoultancommunity.utils.Constants.CHILD_OF_ADS_REALTIME
import com.example.manshatsoultancommunity.utils.generateUniqueId
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ManageFragment: Fragment() {
    private lateinit var binding : FragmentManageBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var status = false

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

//        if (status){
//            binding.parentViewManagers.visibilityVisible()
//        }else{
//            binding.parentViewUsers.visibilityVisible()
//        }
        firebaseDatabase = FirebaseDatabase.getInstance()
       binding.publishAdsBtn.setOnClickListener {
           uploadAdsToFirebase()
       }
        binding.statusNewCheckBox.setOnCheckedChangeListener{ _,isChecked ->
            Log.i("sdsadfasfasfas",isChecked.toString())
            status = isChecked
        }

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
            "https://firebasestorage.googleapis.com/v0/b/manshat-soultan-community.appspot.com/o/gym_imag.jpg?alt=media&token=c8268376-9fe9-4649-b7c4-c200226ac5ff",
            descriptionAnnouncement,
            placePostedAnnouncement,
            status,
            "10:00",
            "اعلان ممول بواسطة $owner",
            datesOfWork,
            contactUs
        )
        firebaseDatabase.reference.child(CHILD_OF_ADS_REALTIME).push().setValue(objAnnouncement)
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
            statusNewCheckBox.isChecked = true
        }
    }
}