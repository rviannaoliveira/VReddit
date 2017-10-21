package com.rviannaoliveira.vreddit.listing

import android.app.SearchManager
import android.content.Context
import android.nfc.tech.MifareUltralight
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.data.repository.RedditSharedPreference
import com.rviannaoliveira.vreddit.modal.RedditNewsData
import kotlinx.android.synthetic.main.fragment_listing.*

/**
 * Criado por rodrigo on 18/10/17.
 */
class ListingFragment : Fragment(), ListingInterface.ListingView, SearchView.OnQueryTextListener {
    private val listingPresenter = ListingPresenterImpl(this)
    private var newsAdapter: NewsAdapter? = null
    private var isLoading: Boolean = false
    private lateinit var searchView: SearchView
    private var listState: Parcelable? = null

    companion object {
        private val NEXT_PAGE = "AFTER"
        private val LIST_STATE_KEY = "123"


        fun newInstance(): ListingFragment {
            return ListingFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_listing, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        listingPresenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        recyclew_posts.layoutManager.onRestoreInstanceState(listState)
    }

    override fun onDestroy() {
        super.onDestroy()
        listingPresenter.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(LIST_STATE_KEY, recyclew_posts.layoutManager.onSaveInstanceState())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        listState = savedInstanceState?.getParcelable(LIST_STATE_KEY)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_list, menu)
        val searchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        isLoading = !newText.isNullOrEmpty()
        newsAdapter?.filter(newText)
        return true
    }

    private fun setUI() {
        newsAdapter = NewsAdapter()
        recyclew_posts.adapter = newsAdapter
        recyclew_posts.setHasFixedSize(true)
        recyclew_posts.addOnScrollListener(onScrollListener())
    }

    override fun loadNewReddits(news: List<RedditNewsData>) {
        newsAdapter?.setNews(news)
        isLoading = false
        newsAdapter?.showLoading(isLoading)
    }

    private fun onScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.layoutManager.childCount
                val totalItemCount = recyclerView.layoutManager.itemCount
                val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (!isLoading && visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= MifareUltralight.PAGE_SIZE) {
                    isLoading = true
                    newsAdapter?.showLoading(isLoading)
                    listingPresenter.loadNextPageNewRedditsList(RedditSharedPreference.getValue(NEXT_PAGE))
                }
            }
        }
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE

    }

    override fun error() {
        include_problem_screen.visibility = View.VISIBLE
    }

    override fun saveNextPage(after: String) {
        RedditSharedPreference.insert(NEXT_PAGE, after)
    }
}