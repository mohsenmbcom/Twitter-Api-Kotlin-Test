package com.mohsenmb.twitterauthsearchkotlinsample.view.components

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mohsenmb.twitterauthsearchkotlinsample.BR
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Tweet

/**
 * Created by mohsen on 6/11/18.
 */
class TweetsAdapter(private val tweets: List<Tweet>) : RecyclerView.Adapter<DataBindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.row_tweet, parent, false)

        return DataBindingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        val tweet: Tweet = tweets[position]
        holder.bindVariable(BR.tweet, tweet)
    }
}