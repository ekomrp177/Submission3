package com.example.submission3.parcelable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieParcelable(
    var poster : String? = null,
    var title : String? = null,
    var description : String? = null,
    var rilis : String? = null,
    var score : String? = null,
    var voter : String? = null
): Parcelable