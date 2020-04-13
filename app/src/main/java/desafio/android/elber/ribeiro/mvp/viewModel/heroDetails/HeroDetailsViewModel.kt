package desafio.android.elber.ribeiro.mvp.viewModel.heroDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import desafio.android.elber.ribeiro.model.data.MarvelHeroesModel
import desafio.android.elber.ribeiro.mvp.repository.heroDetails.HeroDetailsRepository
import desafio.android.elber.ribeiro.mvp.viewModel.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HeroDetailsViewModel(private val dashboardRepository: HeroDetailsRepository?,  var hero: MarvelHeroesModel?) : BaseViewModel() {

    private lateinit var comics: MutableLiveData<List<MarvelHeroesModel>>

    fun getComics(): LiveData<List<MarvelHeroesModel>> {
        if (!::comics.isInitialized) {
            carregarHqs()
        }
        return comics
    }

    private fun carregarHqs() {
        comics = MutableLiveData()
        uiScope.launch {
            try {
                showLoading.value = true
                val response = withContext(bgDispatcher) {
                    dashboardRepository?.buscarHqs(hero?.id ?: 0)
                }
                response?.let {
                    showError.value = false
                    comics.value = it.marvelHeroes.toList()
                } ?: run {
                    showError.value = true
                }
            } catch (e: Exception) {
                Timber.e(e.toString())
                showError.value = true
            } finally {
                showLoading.value = false
            }
        }
    }
}