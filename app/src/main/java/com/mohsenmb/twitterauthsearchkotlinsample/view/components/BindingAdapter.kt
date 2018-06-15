package com.mohsenmb.twitterauthsearchkotlinsample.view.components

import android.databinding.BindingAdapter
import android.net.Uri
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Media
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, medias: List<Media>?) {
        if (medias == null) {
            view.visibility = View.GONE
        } else if (medias[0].type.contentEquals("photo")) {
            val url = medias[0].mediaUrl
            if (url != null) {
                Picasso
                        .get()
                        .load(Uri.parse(url))
                        .centerCrop()
                        .fit()
                        .into(view)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("avatar")
    fun setAvatarUrl(view: ImageView, url: String?) {
        if (url != null) {
            Picasso
                    .get()
                    .load(Uri.parse(url))
                    .fit()
                    .transform(PicassoCircleTransformer())
                    .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("date")
    fun setDate(view: TextView, dateStr: String) {
        val defaultFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy")
        val dateFormat = SimpleDateFormat("EEE MMM dd yyyy   HH:mm:ss")
        view.text = dateFormat.format(defaultFormat.parse(dateStr))
    }

    @JvmStatic
    @BindingAdapter("tweet")
    fun setTweet(view: TextView, tweet: String) {
        val newText: String = tweet.replace("#\\w+".toRegex()) {
            "<a href=\"${it.value}\">${it.value}</a>"
        }
        view.text = Html.fromHtml(newText)
    }
}