package desafio.android.elber.ribeiro.model.response.marvel.comics

import com.google.gson.annotations.SerializedName
import desafio.android.elber.ribeiro.model.response.marvel.common.MarvelCommonResponse

data class MarvelComicsResponse(
    @SerializedName("data") val heroData: MarvelComicsData
): MarvelCommonResponse()