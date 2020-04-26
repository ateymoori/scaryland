package com.pixabay.utils.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserModel (
    val avatar: String?,
    val name: String?,
    val family: String?,
    val givenName: String?,
    val email: String?,
    val id: String?
):Parcelable

