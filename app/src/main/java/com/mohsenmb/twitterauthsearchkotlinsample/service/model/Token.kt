package com.mohsenmb.twitterauthsearchkotlinsample.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by mohsen on 6/10/18.
 */
data class Token(@SerializedName("token_type") @Expose val tokenType: String,
                 @SerializedName("access_token") @Expose val accessToken: String)