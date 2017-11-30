package com.rviannaoliveira.vreddit.listing

import android.app.SearchManager
import android.content.Context
import android.nfc.tech.MifareUltralight
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.rviannaoliveira.vreddit.R
import com.rviannaoliveira.vreddit.core.data.DataManager
import com.rviannaoliveira.vreddit.core.di.VRedditInjector
import com.rviannaoliveira.vreddit.core.extensions.isConnectedToInternet
import com.rviannaoliveira.vreddit.core.model.NewsData
import com.rviannaoliveira.vreddit.main.MainActivity
import kotlinx.android.synthetic.main.fragment_listing.*
import kotlinx.android.synthetic.main.problem_screen.*
import javax.inject.Inject

/**
 * Criado por rodrigo on 18/10/17.
 */
class ListingFragment : Fragment(), ListingInterface.ListingView, SearchView.OnQueryTextListener {
    @Inject
    lateinit var dataManager : DataManager
    private lateinit var listingPresenter : ListingInterface.ListingPresenter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var searchView: SearchView
    private var isLoading: Boolean = false
    private var nextPage: String? = null

    companion object {
        fun newInstance(): ListingFragment {
            return ListingFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_listing, container, false)
        setHasOptionsMenu(true)
        VRedditInjector.vRedditComponent.inject(this)
        listingPresenter = ListingPresenterImpl(this,dataManager)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        listingPresenter.onViewCreated( activity.isConnectedToInternet())
    }

    override fun onDestroy() {
        listingPresenter.onDestroy()
        super.onDestroy()
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
        newsAdapter.filter(newText)
        return true
    }

    private fun setUI() {
        newsAdapter = NewsAdapter(activity as MainActivity)
        recyclew_posts.adapter = newsAdapter
        recyclew_posts.setHasFixedSize(true)
        recyclew_posts.addOnScrollListener(onScrollListener())
    }

    override fun loadNewReddits(news: List<NewsData>) {
        if (news.isNotEmpty()) {
            newsAdapter.setNews(news)
            isLoading = false
            newsAdapter.showLoading(isLoading)
        } else {
            include_problem_screen.visibility = View.VISIBLE
            text_problem.visibility = View.VISIBLE
            text_problem.text = getString(R.string.ops_no_posts)
        }
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
                    newsAdapter.showLoading(isLoading)
                    listingPresenter.loadNextPageNewRedditsList(nextPage)
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
        nextPage = after
    }
}