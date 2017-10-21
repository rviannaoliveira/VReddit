package com.rviannaoliveira.vreddit.detail

import com.rviannaoliveira.vreddit.data.DataManagerFactory
import com.rviannaoliveira.vreddit.data.DataManagerInterface

/**
 * Criado por rodrigo on 20/10/17.
 */
class DetailPresenterImpl(private var detailView: DetailInterface.DetailView?,
                          private var dataManagerInterface: DataManagerInterface? = DataManagerFactory.getDefaultInstance()) : DetailInterface.DetailPresenter {

    override fun onViewCreated() {
        detailView?.showProgressBar()
        loadComments()
    }

    override fun onDestroy() {
        detailView = null
        dataManagerInterface = null
    }

    private fun loadComments() {

    }
}
