package desafio.android.elber.ribeiro.commons.utils.snap

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber


class PagerSnapCallbackHelper : PagerSnapHelper() {

    private var  onSnapFinishCallback : OnSnapFinishCallback

    init {
        this.onSnapFinishCallback = object : OnSnapFinishCallback {
            override fun onSnapFinished(position: Int) {
                Timber.d("\n" +
                        "Snap terminou !! posição = %s", position)
            }
        }
    }

    @SuppressLint("TimberArgCount")
    @Throws(IllegalStateException::class)
    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)

        if (recyclerView == null) {
            return
        }

        var layoutManager: LinearLayoutManager? = null
        try {
            layoutManager = recyclerView.layoutManager as LinearLayoutManager?
        } catch (e: Exception) {
            Timber.w("PagerSnapCallbackHelper \n" +
                    "deve ser usado com um LinearLayoutManager", e)
        }


        if (layoutManager == null) {
            return
        }

        val finalLayoutManager = layoutManager

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val visibleItem = getFirstVisiblePosition(finalLayoutManager)

                    if (visibleItem == RecyclerView.NO_POSITION) {
                        return
                    }

                    this@PagerSnapCallbackHelper.onSnapFinishCallback.onSnapFinished(visibleItem)
                }
            }
        })
    }

    private fun getFirstVisiblePosition(layoutManager: LinearLayoutManager): Int {
        var visibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
        if (visibleItem == RecyclerView.NO_POSITION) {
            visibleItem = layoutManager.findFirstVisibleItemPosition()
        }

        return visibleItem
    }

    interface OnSnapFinishCallback {
        fun onSnapFinished(position: Int)
    }
}