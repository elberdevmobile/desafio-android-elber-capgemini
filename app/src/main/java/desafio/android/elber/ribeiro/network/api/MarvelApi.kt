package desafio.android.elber.ribeiro.network.api

import desafio.android.elber.ribeiro.commons.Definitions
import desafio.android.elber.ribeiro.commons.Definitions.PARAM_CHARACTER_ID
import desafio.android.elber.ribeiro.model.response.marvel.comics.MarvelComicsResponse
import desafio.android.elber.ribeiro.model.response.marvel.hero.MarvelHeroResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    fun getMarvelHeroesAsync(@Query(Definitions.PARAM_LIMIT) limit: Int, @Query(Definitions.PARAM_OFFSET) offset: Int): Deferred<MarvelHeroResponse>

    @GET("characters/{$PARAM_CHARACTER_ID}/comics")
    fun getMarvelComicsAsync(@Path(PARAM_CHARACTER_ID) heroId: Int): Deferred<MarvelComicsResponse>
}