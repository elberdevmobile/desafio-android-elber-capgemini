package desafio.android.elber.ribeiro.mvp.repository.heroDetails

import desafio.android.elber.ribeiro.model.data.MarvelHeroesModel
import desafio.android.elber.ribeiro.model.data.MarvelListModel
import desafio.android.elber.ribeiro.mvp.repository.base.BaseRepository
import desafio.android.elber.ribeiro.network.client.MarvelClient

class HeroDetailsRepository(private val marvelClient: MarvelClient?) : BaseRepository() {
    fun selector(p: MarvelHeroesModel): String = p.price
    suspend fun buscarHqs(heroId: Int): MarvelListModel? {
        val response = try {
            marvelClient?.getMarvelComicsAsync(heroId)?.await()
        } catch (e: Exception) {
            return null
        }

        val marvelModelList = ArrayList(response?.heroData?.results?.map { marvelHero ->
            return@map MarvelHeroesModel(
                    marvelHero.id,
                    marvelHero.title ?: "",
                    marvelHero.description ?: "",
                    marvelHero.thumbnail.path + "." + marvelHero.thumbnail.extension,
                marvelHero.prices[0].price)
        })
        marvelModelList.sortByDescending({selector(it)})
        val list: ArrayList<MarvelHeroesModel> = ArrayList()
        list.add(marvelModelList[0])

        return MarvelListModel(list)
    }
}