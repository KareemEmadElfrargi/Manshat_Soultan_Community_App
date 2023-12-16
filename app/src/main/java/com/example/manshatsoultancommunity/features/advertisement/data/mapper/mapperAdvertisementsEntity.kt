package com.example.manshatsoultancommunity.features.advertisement.data.mapper

import com.example.manshatsoultancommunity.features.advertisement.data.data_source.local.Entity.AdvertisementsEntity
import com.example.manshatsoultancommunity.features.advertisement.data.model.Advertisements


fun Advertisements.toRoomEntity(imageDataAfterConvertToByte:ByteArray): AdvertisementsEntity {
    return AdvertisementsEntity(
        id = id,
        titleOfAdvertisement = titleOfAdvertisement,
        imageOfAdvertisement = imageOfAdvertisement,
        imageDataOfAdvertisement =imageDataAfterConvertToByte,
        descriptionOfAdvertisement = descriptionOfAdvertisement,
        locationOfAdvertisement = locationOfAdvertisement,
        isPinAdvertisement = isPinAdvertisement,
        datePostedAdvertisement = datePostedAdvertisement,
        ownerOfAdvertisement = ownerOfAdvertisement,
        datesOfAdvertisement = datesOfAdvertisement,
        numberOfOwnerOfAdvertisement = numberOfOwnerOfAdvertisement
    )
}
