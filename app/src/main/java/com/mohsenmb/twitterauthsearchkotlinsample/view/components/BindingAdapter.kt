package com.mohsenmb.twitterauthsearchkotlinsample.view.components

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.mohsenmb.twitterauthsearchkotlinsample.util.toUri
import com.squareup.picasso.Picasso

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String?) {
        if (url != null) {
            Picasso
                    .get()
                    .load(url.toUri())
                    .fit()
                    .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("avatar")
    fun setAvatarUrl(view: ImageView, url: String?) {
        if (url != null) {
            Picasso
                    .get()
                    .load(url.toUri())
                    .fit()
                    .transform(PicassoCircleTransformer())
                    .into(view)
        }
    }
}