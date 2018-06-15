package com.mohsenmb.twitterauthsearchkotlinsample.service

import com.mohsenmb.twitterauthsearchkotlinsample.service.model.SearchResponse
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Token
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by mohsen on 6/10/18.
 */
interface TwitterWebService {
    @FormUrlEncoded
    @POST("/oauth2/token")
    @Headers(
            "Content-Type: application/x-www-form-urlencoded;charset=UTF-8"
    )
    fun authorize(@Header("Authorization") basicAuthorization: String,
                  @Field("grant_type") grantType: String = "client_credentials"): Observable<Token>

    @GET("/1.1/search/tweets.json")
    fun searchTweets(@Header("Authorization") bearerToken: String,
                     @Query("q") query: String,
                     @Query("count") count: Int = 10): Observable<SearchResponse>

    @GET("/1.1/search/tweets.json")
    fun loadSearchNextPage(@Header("Authorization") bearerToken: String,
                           @Query("q") query: String,
                           @Query("since_id") maxId: String,
                           @Query("include_entities") includeEntities: Int = 1,
                           @Query("count") count: Int = 10): Observable<SearchResponse>
}