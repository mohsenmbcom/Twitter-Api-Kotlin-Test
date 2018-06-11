package com.mohsenmb.twitterauthsearchkotlinsample.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by mohsen on 6/11/18.
 */
data class SearchResponse(@SerializedName("statuses") @Expose val tweets: List<Tweet>,
                          @SerializedName("search_metadata") @Expose val searchMetaData: SearchMetaData)

data class SearchMetaData(@SerializedName("query") @Expose val query: String,
                          @SerializedName("next_results") @Expose val nextResults: String)