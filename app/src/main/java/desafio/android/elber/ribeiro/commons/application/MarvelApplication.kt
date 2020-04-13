package desafio.android.elber.ribeiro.commons.application

import android.app.Application
import desafio.android.elber.ribeiro.R
import desafio.android.elber.ribeiro.network.client.MarvelClient
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.BuildConfig
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.lang.ref.WeakReference

class MarvelApplication : Application() {

    companion object {
        private var instance: WeakReference<MarvelApplication>? = null

        @JvmStatic
        fun get(): MarvelApplication? {
            return instance?.get()
        }
    }

    val marvelClient: MarvelClient by lazy {
        return@lazy MarvelClient()
    }

    override fun onCreate() {
        super.onCreate()
        instance = WeakReference(this)
        initCalligraphy()
        initTimber()
    }

    private fun initCalligraphy() {
        CalligraphyConfig.initDefault(
                CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Marvel-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        )
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

}