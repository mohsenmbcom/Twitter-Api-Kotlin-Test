package com.mohsenmb.twitterauthsearchkotlinsample.view.components

import android.databinding.BindingAdapter
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Media
import com.squareup.picasso.Picasso

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
}