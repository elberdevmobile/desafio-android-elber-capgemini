package desafio.android.elber.ribeiro.model.response.marvel.common

import com.google.gson.annotations.SerializedName

data class MarvelPrices(
    @SerializedName("printPrice") val printPrice: String,
    @SerializedName("price") val price: String
)