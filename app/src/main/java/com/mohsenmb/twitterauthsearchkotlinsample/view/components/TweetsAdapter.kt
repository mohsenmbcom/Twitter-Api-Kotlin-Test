package com.mohsenmb.twitterauthsearchkotlinsample.view.components

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mohsenmb.twitterauthsearchkotlinsample.BR
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Tweet
import com.mohsenmb.twitterauthsearchkotlinsample.util.OnLinkClickedListener
import com.mohsenmb.twitterauthsearchkotlinsample.util.TextViewMovementMethod
import kotlinx.android.synthetic.main.row_tweet.view.*

/**
 * Created by mohsen on 6/11/18.
 */
class TweetsAdapter(private val tweets: List<Tweet>) : RecyclerView.Adapter<DataBindingViewHolder>() {
    lateinit var onHashtagClickListener: OnHashtagClickListener
    lateinit var onTweetClickListener: OnTweetClickListener
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
        holder.itemView.tvTweetText.linksClickable = true
        holder.itemView.tvTweetText.movementMethod = TextViewMovementMethod(object : OnLinkClickedListener {
            override fun onLinkClicked(url: String): Boolean {
                if (::onHashtagClickListener.isInitialized) {
                    onHashtagClickListener.onHashtagClicked(url)
                    return false
                }
                return true
            }
        })
        holder.itemView.setOnClickListener {
            if (::onTweetClickListener.isInitialized) {
                onTweetClickListener.onTweetClicked(tweets[holder.adapterPosition])
            }
        }
        holder.itemView.btnViewTweet.setOnClickListener {
            holder.itemView.callOnClick()
        }
    }
}

interface OnHashtagClickListener {
    fun onHashtagClicked(hashtag: String)
}
interface OnTweetClickListener {
    fun onTweetClicked(tweet: Tweet)
}