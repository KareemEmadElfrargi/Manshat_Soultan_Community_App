package com.example.manshatsoultancommunity.features.Intro.data.model

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.encoders.annotations.Encodable.Ignore
import java.io.Serializable

@IgnoreExtraProperties
data class Admin(
    val id:String,
    val name:String,
    val category:List<String>,
    val active:Boolean = false,
    val rating:Int
):Serializable{
    constructor():this("","",emptyList<String>(),false,1)
}
