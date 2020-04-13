package desafio.android.elber.ribeiro.model.response.commor

interface IResponse {
    fun isSuccess(): Boolean

    fun getErrorMessage(): String
}