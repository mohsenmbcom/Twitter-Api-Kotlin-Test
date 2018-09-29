package com.mohsenmb.twitterauthsearchkotlinsample.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mohsenmb.twitterauthsearchkotlinsample.BR
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Tweet


/**
 * Created by mohsen on 6/15/18.
 */
const val ARG_TWEET = "tweet"
class TweetFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                inflater, R.layout.fragment_tweet, container, false)
        val view = binding.root
        val tweet = arguments?.getParcelable(ARG_TWEET) as Tweet
        binding.setVariable(BR.tweet, tweet)
        return view
    }

    override fun showProgress() {}

    override fun hideProgress() {}

    override fun retry() {}

}