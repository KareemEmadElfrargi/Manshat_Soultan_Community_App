package com.example.manshatsoultancommunity.utils.dailogs

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.DetailsOfAdsDialogBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.AnnouncementPost
import com.google.android.material.bottomsheet.BottomSheetDialog

@SuppressLint("MissingInflatedId")
fun Fragment.setupButtonSheetDetailsDialog(
    adsPost: AnnouncementPost,
    onConfirmClick:(String)->Unit
){
    val dialog = BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialog)
    val binding : DetailsOfAdsDialogBinding = DetailsOfAdsDialogBinding.inflate(layoutInflater)
    dialog.setContentView(requireNotNull(binding.root))
    dialog.show()

    binding.apply {
        tvOwnerTitle.text = adsPost.owner
        tvDescriptionAds.text = adsPost.descriptionAnnouncement
        tvDaysAndDateOfAds.text = adsPost.dates
        tvPlaceOfAds.text = adsPost.placePostedAnnouncement
        tvDatePublishedOfAds.text = adsPost.datePostedAnnouncement
        buttonCancelBackAds.setOnClickListener {
            dialog.dismiss()
        }
        buttonEnquiryOfAds.setOnClickListener {
            val phoneNumber = adsPost.contactUs ?: "01113461483"
            onConfirmClick(phoneNumber)
            dialog.dismiss()
        }
    }
}