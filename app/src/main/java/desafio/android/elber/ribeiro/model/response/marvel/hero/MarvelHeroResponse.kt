package desafio.android.elber.ribeiro.model.response.marvel.hero

import com.google.gson.annotations.SerializedName
import desafio.android.elber.ribeiro.model.response.marvel.common.MarvelCommonResponse

data class MarvelHeroResponse(@SerializedName("data") val heroData: MarvelHeroData): MarvelCommonResponse()