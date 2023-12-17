package com.example.manshatsoultancommunity.utils.dailogs

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.DetailsOfAdsDialogBinding
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements
import com.google.android.material.bottomsheet.BottomSheetDialog

@SuppressLint("MissingInflatedId")
fun Fragment.setupButtonSheetDetailsDialog(
    adsPost: Advertisements,
    onConfirmClick:(String)->Unit
){
    val dialog = BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialog)
    val binding : DetailsOfAdsDialogBinding = DetailsOfAdsDialogBinding.inflate(layoutInflater)
    dialog.setContentView(requireNotNull(binding.root))
    dialog.show()

    binding.apply {
        tvOwnerTitle.text = adsPost.ownerOfAdvertisement
        tvDescriptionAds.text = adsPost.descriptionOfAdvertisement
        tvDaysAndDateOfAds.text = adsPost.datesOfAdvertisement
        tvPlaceOfAds.text = adsPost.locationOfAdvertisement
        tvDatePublishedOfAds.text = " تاريخ نشر الاعلان : ${adsPost.datePostedAdvertisement}"
        buttonCancelBackAds.setOnClickListener {
            dialog.dismiss()
        }
        buttonEnquiryOfAds.setOnClickListener {
            val phoneNumber = adsPost.numberOfOwnerOfAdvertisement ?: "01113461483"
            onConfirmClick(phoneNumber)
            dialog.dismiss()
        }
    }
}