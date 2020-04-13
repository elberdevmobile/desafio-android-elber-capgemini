package desafio.android.elber.ribeiro.ui.activity.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import desafio.android.elber.ribeiro.mvp.viewModel.base.BaseViewModel

@SuppressLint("Registered")
open class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    protected var viewModel: T? = null
}