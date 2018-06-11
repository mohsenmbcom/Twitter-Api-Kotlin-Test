package com.mohsenmb.twitterauthsearchkotlinsample.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by mohsen on 6/10/18.
 */
data class Tweet(@SerializedName("created_at") @Expose val createdAt: String,
                 @SerializedName("id_str") @Expose val id: String,
                 @SerializedName("text") @Expose val text: String,
                 @SerializedName("user") @Expose val user: User,
                 @SerializedName("entities") @Expose val entities: Entities) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(User::class.java.classLoader),
            parcel.readParcelable(Entities::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdAt)
        parcel.writeString(id)
        parcel.writeString(text)
        parcel.writeParcelable(user, flags)
        parcel.writeParcelable(entities, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tweet> {
        override fun createFromParcel(parcel: Parcel): Tweet {
            return Tweet(parcel)
        }

        override fun newArray(size: Int): Array<Tweet?> {
            return arrayOfNulls(size)
        }
    }
}