package desafio.android.elber.ribeiro.mvp.viewModel.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import desafio.android.elber.ribeiro.model.data.MarvelHeroesModel
import desafio.android.elber.ribeiro.mvp.repository.dashboard.DashboardRepository
import desafio.android.elber.ribeiro.mvp.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class DashboardViewModel(private val dashboardRepository: DashboardRepository?) : BaseViewModel() {

    private lateinit var heroes: MutableLiveData<List<MarvelHeroesModel>>
    private var heroesList = arrayListOf<MarvelHeroesModel>()
    private var limit = 20
    private var offset = 0
    private var isFetching = false


    fun getHeroes(): LiveData<List<MarvelHeroesModel>> {
        if (!::heroes.isInitialized) {
            heroes = MutableLiveData()
            carregarHerois()
        }
        return heroes
    }

    fun carregarHerois() {
        if (isFetching()) {
            return
        }
        setFetching(true)

        uiScope.launch {
            try {
                if (offset == 0) {
                    showLoading.value = true
                }
                val response = withContext(bgDispatcher) { dashboardRepository?.buscarHerois(offset, limit) }
                response?.let {
                    showError.value = false
                    offset += limit
                    heroesList.addAll(it.marvelHeroes)
                    heroes.value = heroesList
                } ?: run {
                    showError.value = true
                }
            } catch (e: Exception) {
                Timber.e(e.toString())
                showError.value = true
            } finally {
                showLoading.value = false
                setFetching(false)
            }
        }
    }

    @Synchronized
    private fun isFetching(): Boolean {
        return this.isFetching
    }

    @Synchronized
    private fun setFetching(isFetching: Boolean) {
        this.isFetching = isFetching
    }
}