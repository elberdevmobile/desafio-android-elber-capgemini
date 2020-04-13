package desafio.android.elber.ribeiro.model.response.marvel.hero

import com.google.gson.annotations.SerializedName
import desafio.android.elber.ribeiro.model.response.marvel.common.MarvelThumbnail

data class MarvelHero(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("thumbnail") val thumbnail: MarvelThumbnail
)