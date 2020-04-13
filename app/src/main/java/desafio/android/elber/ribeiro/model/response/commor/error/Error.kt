package desafio.android.elber.ribeiro.model.response.commor.error

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("name") val name: String = "",
    @SerializedName("errorMessage") val errorMessage: String = ""
)