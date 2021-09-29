package br.com.marciosouza.googlebooks.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Volume(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo,
): Parcelable