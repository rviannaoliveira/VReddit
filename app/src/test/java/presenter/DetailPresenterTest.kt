package presenter

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.rviannaoliveira.vreddit.BuildConfig
import com.rviannaoliveira.vreddit.data.DataManagerInterface
import com.rviannaoliveira.vreddit.detail.DetailInterface
import com.rviannaoliveira.vreddit.detail.DetailPresenterImpl
import com.rviannaoliveira.vreddit.modal.RedditCommentData
import io.reactivex.Maybe
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config

/**
 * Criado por rodrigo on 21/10/17.
 */
@RunWith(MockitoJUnitRunner::class)
@Config(constants = BuildConfig::class)
class DetailPresenterTest {
    @Mock private
    lateinit var detailView: DetailInterface.DetailView

    @Mock private
    lateinit var dataManager: DataManagerInterface

    @Mock private
    lateinit var mockList: MutableList<RedditCommentData>

    @Test
    fun loadingNewsPostsInTheListingView() {
        val hasInternet = true
        val after = "ac4rcd"
        val detailPresenter = getDetailPresenter()

        whenever(dataManager.getAllCommentsNew(Mockito.anyString())).thenReturn(Maybe.just(mockList))
        detailPresenter.onViewCreated(after, hasInternet)

        verify(this.detailView).showProgressBar()
        verify(this.detailView).loadComments(ArgumentMatchers.anyList<RedditCommentData>())
        verify(this.detailView).hideProgressBar()
    }

    private fun getDetailPresenter(): DetailPresenterImpl {
        return DetailPresenterImpl(detailView, dataManager)
    }
}