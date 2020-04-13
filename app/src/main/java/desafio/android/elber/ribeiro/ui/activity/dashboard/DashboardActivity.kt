package desafio.android.elber.ribeiro.ui.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import desafio.android.elber.ribeiro.R
import desafio.android.elber.ribeiro.commons.BUNDLE
import desafio.android.elber.ribeiro.commons.Definitions
import desafio.android.elber.ribeiro.commons.application.MarvelApplication
import desafio.android.elber.ribeiro.database.MarvelDatabase
import desafio.android.elber.ribeiro.mvp.repository.base.BaseViewModelFactory
import desafio.android.elber.ribeiro.mvp.repository.dashboard.DashboardRepository
import desafio.android.elber.ribeiro.mvp.viewModel.dashboard.DashboardViewModel
import desafio.android.elber.ribeiro.ui.activity.base.BaseActivity
import desafio.android.elber.ribeiro.ui.activity.heroDetails.HeroDetailsActivity
import desafio.android.elber.ribeiro.ui.adapters.dashboard.DashboardRecyclerViewAdapter
import desafio.android.elber.ribeiro.ui.custom.pagination.PaginationScrollListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.layout_pagination_recyclerview.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DashboardActivity : BaseActivity<DashboardViewModel>() {

    private var dashboardRecyclerViewAdapter: DashboardRecyclerViewAdapter? = null
    private var paginationScrollListener: PaginationScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initLayout()
        initViewModel()
    }

    private fun initViewModel() {
        val dashboardViewModelFactory = BaseViewModelFactory {
            DashboardViewModel(DashboardRepository(MarvelApplication.get()?.marvelClient, MarvelDatabase.get(this)))
        }
        /**
         * ViewModelProviders , keeping the ViewModel alive and paired with the scope
         */
        viewModel = ViewModelProviders.of(this, dashboardViewModelFactory).get(DashboardViewModel::class.java)
        viewModel?.getHeroes()?.observe(this, Observer { heroes ->
            // update UI
            moreProgressView?.visibility = View.GONE
            heroes?.let {
                dashboardRecyclerViewAdapter?.setHeroesList(it.toMutableList())
            } ?: run { emptyView.visibility = View.VISIBLE }
        })

        viewModel?.getIsLoading()?.observe(this, Observer { value ->
            value?.let { show ->
                loadingView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })

        viewModel?.shouldShowError()?.observe(this, Observer { value ->
            value?.let { show ->
                emptyView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

    private fun initLayout() {
        toolbarTitleTextView.text = getString(R.string.dashboard_toolbar_title)
        backButtonImageView.visibility = View.INVISIBLE
        closeButtonImageView.visibility = View.INVISIBLE
        val linearLayoutManager = LinearLayoutManager(this)
        dashboardRecyclerView.layoutManager = linearLayoutManager
        dashboardRecyclerViewAdapter = DashboardRecyclerViewAdapter(
                onHeroClicked = { hero ->
                    val intent = Intent(this, HeroDetailsActivity::class.java)
                    intent.putExtra(BUNDLE.HERO_DETAILS, hero)
                    startActivity(intent)
                })

        paginationScrollListener = PaginationScrollListener(
                linearLayoutManager,
                {
                    moreProgressView?.visibility = View.VISIBLE
                    viewModel?.carregarHerois()
                },
                Definitions.PAGINATION_SIZE
        )
        paginationScrollListener?.let {
            dashboardRecyclerView.addOnScrollListener(it)
        }

        dashboardRecyclerView.adapter = dashboardRecyclerViewAdapter

    }
}
