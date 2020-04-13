package desafio.android.elber.ribeiro.ui.activity.heroDetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import desafio.android.elber.ribeiro.R
import desafio.android.elber.ribeiro.commons.BUNDLE
import desafio.android.elber.ribeiro.commons.application.MarvelApplication
import desafio.android.elber.ribeiro.commons.extentions.getScreenWidth
import desafio.android.elber.ribeiro.commons.utils.snap.PagerSnapCallbackHelper
import desafio.android.elber.ribeiro.model.data.MarvelHeroesModel
import desafio.android.elber.ribeiro.mvp.repository.base.BaseViewModelFactory
import desafio.android.elber.ribeiro.mvp.repository.heroDetails.HeroDetailsRepository
import desafio.android.elber.ribeiro.mvp.viewModel.heroDetails.HeroDetailsViewModel
import desafio.android.elber.ribeiro.ui.activity.base.BaseActivity
import desafio.android.elber.ribeiro.ui.adapters.heroDetails.HeroDetailsRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_hero_details.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class HeroDetailsActivity : BaseActivity<HeroDetailsViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_details)
        initViewModel()
        initLayout()
    }

    private fun initLayout() {
        backButtonImageView.setOnClickListener { onBackPressed() }
        toolbarTitleTextView.text = getString(R.string.dashboard_toolbar_title)
        Picasso.get().load(viewModel?.hero?.thumbnail).into(heroImageView)
        heroTitleTextView.text = viewModel?.hero?.name
        heroDescriptionTextView.text = if (viewModel?.hero?.description.isNullOrEmpty()) getString(R.string.dummy_description) else viewModel?.hero?.description
    }

    private fun initViewModel() {
        val heroDetailsViewModelFactory = BaseViewModelFactory {
            HeroDetailsViewModel(HeroDetailsRepository(MarvelApplication.get()?.marvelClient), intent?.extras?.getParcelable<MarvelHeroesModel>(BUNDLE.HERO_DETAILS))
        }
        viewModel = ViewModelProviders.of(this, heroDetailsViewModelFactory).get(HeroDetailsViewModel::class.java)

        viewModel?.getComics()?.observe(this, Observer { heroes ->
            heroes?.let {
                heroDetailsRecyclerView.setHasFixedSize(true)
                heroDetailsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                heroDetailsRecyclerView.adapter = HeroDetailsRecyclerViewAdapter(it.toMutableList(), (getScreenWidth(this) * 0.85).toInt())

                val pagerSnapCallbackHelper = PagerSnapCallbackHelper()
                pagerSnapCallbackHelper.attachToRecyclerView(heroDetailsRecyclerView)
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

        bthqs.setOnClickListener(){
            val intent = Intent(this@HeroDetailsActivity, HqMoreExpensive::class.java)
            intent.putExtra(BUNDLE.HERO_DETAILS, viewModel?.hero)
            startActivity(intent)

        }
    }
}
