package birthdaygreetings.birthdayframe.greetingswishes.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import birthdaygreetings.birthdayframe.greetingswishes.activity.MainActivity
import birthdaygreetings.birthdayframe.greetingswishes.databinding.SplashLayoutBinding
import birthdaygreetings.birthdayframe.greetingswishes.util.AdUtils
import com.sm.newadlib.app.LibSplashActivity
import com.sm.newadlib.handlers.AdsHandler


class SplashActivity : LibSplashActivity() {

    private var handler: Handler? = null
    private lateinit var b: SplashLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = SplashLayoutBinding.inflate(layoutInflater)
        setContentView(b.root)
        AdUtils.enabledisable(this)
        AdsHandler.initAdConfig(this)
        AdUtils.showAdaptiveBanner(this, b.nativeAdContainer)
        handler = Handler(mainLooper)

        scheduleSplashScreen()
    }

    private fun goToMainActivity() {
        if (handler != null) {
            handler!!.removeCallbacksAndMessages(null)
            AdUtils.showEntryFullAd(this, object : AdUtils.AdListener {
                override fun onComplete() {
                    AdUtils.adDisplayTime = System.currentTimeMillis()
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            })
        }
    }

    private fun scheduleSplashScreen() {
        val SPLASH_TIME_OUT = 1000
        handler!!.postDelayed({ goToMainActivity() }, SPLASH_TIME_OUT.toLong())
    }


}