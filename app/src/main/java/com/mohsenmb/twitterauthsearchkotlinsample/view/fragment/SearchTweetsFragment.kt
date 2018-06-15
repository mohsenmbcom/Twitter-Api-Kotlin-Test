package com.mohsenmb.twitterauthsearchkotlinsample.view.fragment

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.mohsenmb.twitterauthsearchkotlinsample.R
import com.mohsenmb.twitterauthsearchkotlinsample.TwitterApplication
import com.mohsenmb.twitterauthsearchkotlinsample.di.SearchTweetsModule
import com.mohsenmb.twitterauthsearchkotlinsample.presentation.SearchPresenter
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Tweet
import com.mohsenmb.twitterauthsearchkotlinsample.util.hideKeyboard
import com.mohsenmb.twitterauthsearchkotlinsample.util.isConnected
import com.mohsenmb.twitterauthsearchkotlinsample.view.SearchTweetsView
import com.mohsenmb.twitterauthsearchkotlinsample.view.components.OnHashtagClickListener
import com.mohsenmb.twitterauthsearchkotlinsample.view.components.TweetsAdapter
import kotlinx.android.synthetic.main.fragment_search_tweets.*
import javax.inject.Inject

/**
 * Created by mohsen on 6/11/18.
 */
class SearchTweetsFragment : BaseFragment(), SearchTweetsView {

    init {
        TwitterApplication
                .INSTANCE
                .component
                ?.plus(SearchTweetsModule(this))
                ?.inject(this)
    }

    private var firstPage = true
    private var canLoadMore: Boolean = true
    var tweets: MutableList<Tweet> = mutableListOf()

    @Inject
    lateinit var presenter: SearchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_tweets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (v.text.trim().length > 2) {
                    firstPage = true
                    presenter.searchTweets(context?.isConnected()
                            ?: false, v.text.toString().trim())
                }
            }
            true
        }
        srlTweets.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorPrimary), ContextCompat.getColor(context!!, R.color.colorAccent))
        srlTweets.setProgressViewOffset(false, 0, resources.getDimension(R.dimen.progress_top_padding).toInt())
        srlTweets.setOnRefreshListener {
            search()
        }
        val tweetsAdapter = TweetsAdapter(tweets)
        tweetsAdapter.onHashtagClickListener = object : OnHashtagClickListener {
            override fun onHashtagClicked(hashtag: String) {
                etSearch.setText(hashtag)
                search()
            }
        }
        rvTweets.adapter = tweetsAdapter
        rvTweets.layoutManager = LinearLayoutManager(context)
        rvTweets.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager: LinearLayoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                if (layoutManager.findLastVisibleItemPosition() == recyclerView.adapter.itemCount - 1) {
                    if (canLoadMore && !srlTweets.isRefreshing) {
                        firstPage = false
                        presenter.loadNextPage(context?.isConnected() ?: false)
                    }
                }
            }
        })
    }

    private fun search() {
        if (etSearch.text.trim().length > 2) {
            firstPage = true
            presenter.searchTweets(context?.isConnected()
                    ?: false, etSearch.text.toString().trim())
        } else {
            srlTweets.isRefreshing = false
        }
    }

    override fun showTweets(loadedTweets: List<Tweet>, canLoadMore: Boolean) {
        activity?.hideKeyboard()
        this.canLoadMore = canLoadMore
        if (firstPage) {
            val count = this.tweets.size
            this.tweets.clear()
            this.tweets.addAll(loadedTweets)
            rvTweets.adapter.notifyItemRangeRemoved(0, count)
            rvTweets.adapter.notifyItemRangeInserted(0, loadedTweets.size)
        } else {
            val lastIndex = this.tweets.size
            this.tweets.addAll(loadedTweets)
            rvTweets.adapter.notifyItemRangeInserted(lastIndex, loadedTweets.size)
        }
        Log.e("COUNT", "Count=${rvTweets.adapter.itemCount}")
    }

    override fun hideProgress() {
        srlTweets.isRefreshing = false
    }

    override fun showProgress() {
        srlTweets.isRefreshing = true
    }

    override fun retry() {
        search()
    }
}