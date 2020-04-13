package desafio.android.elber.ribeiro.mvp.viewModel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val viewModelJob = SupervisorJob()
    protected val bgDispatcher: CoroutineDispatcher = Dispatchers.IO
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    protected lateinit var showLoading: MutableLiveData<Boolean>
    protected lateinit var showError: MutableLiveData<Boolean>

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun shouldShowError(): LiveData<Boolean> {
        if (!::showError.isInitialized) {
            showError = MutableLiveData()
        }
        return showError
    }

    fun getIsLoading(): LiveData<Boolean> {
        if (!::showLoading.isInitialized) {
            showLoading = MutableLiveData()
        }
        return showLoading
    }
}