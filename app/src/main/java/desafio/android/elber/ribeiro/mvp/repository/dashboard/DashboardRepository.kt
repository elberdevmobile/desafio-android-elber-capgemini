package desafio.android.elber.ribeiro.mvp.repository.dashboard

import desafio.android.elber.ribeiro.database.MarvelDatabase
import desafio.android.elber.ribeiro.database.dao.MarvelTable
import desafio.android.elber.ribeiro.model.data.MarvelHeroesModel
import desafio.android.elber.ribeiro.model.data.MarvelListModel
import desafio.android.elber.ribeiro.mvp.repository.base.BaseRepository
import desafio.android.elber.ribeiro.network.client.MarvelClient
import timber.log.Timber


class DashboardRepository(private val marvelClient: MarvelClient?, private val marvelDatabase: MarvelDatabase) : BaseRepository() {

    suspend fun buscarHerois(offset: Int, limit: Int): MarvelListModel? {
        val response = try {
            marvelClient?.getMarvelHeroesAsync(limit, offset)?.await()
        } catch (e: Exception) {
            return null
        }

        val marvelModelList = ArrayList(response?.heroData?.results?.map { marvelHero ->
            marvelDatabase.marvelDao().insertHero(MarvelTable(marvelHero.id, false))

            return@map MarvelHeroesModel(
                    marvelHero.id,
                    marvelHero.name,
                    marvelHero.description,
                    marvelHero.thumbnail.path + "." + marvelHero.thumbnail.extension
            )
        })
        return MarvelListModel(marvelModelList)
    }
}