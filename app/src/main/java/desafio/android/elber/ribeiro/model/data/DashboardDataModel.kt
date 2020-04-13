package desafio.android.elber.ribeiro.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelHeroesModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val thumbnail: String = "",
    val price: String = ""
) : Parcelable

@Parcelize
data class MarvelListModel(val marvelHeroes: ArrayList<MarvelHeroesModel> = arrayListOf()) :
    Parcelable