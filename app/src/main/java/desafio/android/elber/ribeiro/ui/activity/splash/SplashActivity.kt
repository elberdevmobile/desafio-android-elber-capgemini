package desafio.android.elber.ribeiro.ui.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import desafio.android.elber.ribeiro.R
import desafio.android.elber.ribeiro.ui.activity.dashboard.DashboardActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initLayout()
    }

    private fun initLayout() {
        Handler().postDelayed({
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }, 2000)
    }


}
