package com.mohsenmb.twitterauthsearchkotlinsample.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by mohsen on 6/11/18.
 */
data class Entities(@SerializedName("media") @Expose val medias: List<Media>?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Media))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(medias)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Entities> {
        override fun createFromParcel(parcel: Parcel): Entities {
            return Entities(parcel)
        }

        override fun newArray(size: Int): Array<Entities?> {
            return arrayOfNulls(size)
        }
    }
}