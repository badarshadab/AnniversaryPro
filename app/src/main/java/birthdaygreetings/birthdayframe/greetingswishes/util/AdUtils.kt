package birthdaygreetings.birthdayframe.greetingswishes.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import birthdaygreetings.birthdayframe.greetingswishes.R
import com.android.volley.AuthFailureError
import com.android.volley.NetworkError
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.ServerError
import com.android.volley.TimeoutError
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.sm.newadlib.handlers.AdsHandler
import com.sm.newadlib.listeners.FullAdListener
import org.json.JSONObject

object AdUtils {

    var isAdsCheck = false
    var adsInterVal: Long = 0
    var adDisplayTime: Long = 0
    var count: Int = 0
    var counter: Int = 0
    var enableSwipeAd = false
    var swipeCount: Int = 0


    fun showEntryFullAd(activity: Activity, listener: AdListener) {
        if (isAdsCheck) {
            AdsHandler.showEntryInterstitialAds(activity, object : FullAdListener {
                override fun onComplete(isAdDisplay: Boolean, adNetwork: String) {
                    adDisplayTime = System.currentTimeMillis()
                    listener.onComplete()

                }
            })
        } else {
            listener.onComplete()
        }

    }

    fun showFullAd(activity: Activity, listener: AdListener) {
        if (isAdsCheck) {
            AdsHandler.showInterstitialAds(activity, object : FullAdListener {
                override fun onComplete(isAdDisplay: Boolean, adNetwork: String) {
                    listener.onComplete()
                }
            })
        } else {
            listener.onComplete()
        }
    }

    fun showSwipeFullAd(activity: Activity, listener: AdListener) {
        if (enableSwipeAd) {
            AdsHandler.showInterstitialAds(activity, object : FullAdListener {
                override fun onComplete(isAdDisplay: Boolean, adNetwork: String) {
                    listener.onComplete()
                }
            })
        } else {
            listener.onComplete()
        }
    }



    fun showNativeBanner(activity: Activity, adContainer: ViewGroup) {
        if (isAdsCheck) {
            adContainer.removeAllViews()
            showAdaptiveBanner(activity, adContainer)
        }
    }

    fun showNative(activity: Activity, adContainer: ViewGroup) {
        if (isAdsCheck) {
            AdsHandler.showNativeAd(activity, adContainer)
        }
    }

    fun showMedRect(activity: Activity, adContainer: ViewGroup) {
        if (isAdsCheck) {
            AdsHandler.showMedBannerAd(activity, adContainer)
        }
    }

    fun showBanner(activity: Activity, adContainer: ViewGroup) {
        if (isAdsCheck) {
            AdsHandler.showBannerAd(activity, adContainer)
        }
    }

    fun showAdaptiveBanner(activity: Activity, adContainer: ViewGroup) {
        if (isAdsCheck) {
            adContainer.removeAllViews()
            showAdaptiveBanner1(activity, adContainer)
        }
    }

    fun showSplashAdaptiveBanner(activity: Activity, adContainer: ViewGroup) {

        adContainer.removeAllViews()
        showSplashAdaptiveBanner1(activity, adContainer)

    }


    interface AdListener {
        fun onComplete()
    }


    interface ServerCallBack {
        fun onSuccess(isSuccess: Boolean, string: String)
    }


    fun enabledisable(context: Context) {
        doStringRequest(
            context,
            context.resources.getString(R.string.enabledisable),
            object : ServerCallBack {
                override fun onSuccess(isSuccess: Boolean, string: String) {
                    if (isSuccess) {
                        try {
                            val ob = JSONObject(string)
                            val vcode = ob.getString("vCode")
                            isAdsCheck = ob.getBoolean("enableCheck")
                            adsInterVal = ob.getLong("adsInterverlSplash")
                            counter = ob.getString("counter").toInt()
                            enableSwipeAd = ob.getBoolean("enableSwipeAd")
                            swipeCount = ob.getString("swipeCount").toInt()
                        } catch (e: Exception) {
                            println("Shadab e ${e.message}")
                        }
                    }
                }
            })
    }


    fun doStringRequest(context: Context, url: String, serverCallBack: ServerCallBack) {
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                serverCallBack.onSuccess(true, response)
            },
            { error ->
                serverCallBack.onSuccess(false, getVolleyError(error))
            })
        ServerRequest.addToRequestQueue(context, stringRequest)
    }

    private fun getVolleyError(volleyError: VolleyError?): String {
        var msg: String
        msg = "Network Error"
        when (volleyError) {
            is NetworkError -> {
                msg = "Cannot connect to Internet...Please check your connection!"
            }

            is ServerError -> {
                msg = "The server could not be found. Please try again after some time!!"
            }

            is AuthFailureError -> {
                msg = "Cannot connect to Internet...Please check your connection!"
            }

            is ParseError -> {
                msg = "Parsing error! Please try again after some time!!"
            }

            is TimeoutError -> {
                msg = "Connection TimeOut! Please check your internet connection."
            }
        }
        return msg
    }

    fun showAdaptiveBanner1(ctx: Activity, container: ViewGroup) {
        if (isAdsCheck) {
            val mAdView = AdView(ctx)
            mAdView.adUnitId = ctx.getString(R.string.admob_banner_ad_id)

            mAdView.setAdSize(getAdaptiveBannerAdSize(ctx))

            // Create an ad request.
            val adRequest = AdRequest.Builder().build()

            // Start loading the ad in the background.
            mAdView.loadAd(adRequest)
            container.addView(mAdView)
        }
    }

    fun showSplashAdaptiveBanner1(ctx: Activity, container: ViewGroup) {

        val mAdView = AdView(ctx)
        mAdView.adUnitId = ctx.getString(R.string.admob_splash_banner)

        mAdView.setAdSize(getAdaptiveBannerAdSize(ctx))

        // Create an ad request.
        val adRequest = AdRequest.Builder().build()

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest)
        container.addView(mAdView)
    }

    fun getAdaptiveBannerAdSize(activity: Activity): AdSize {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        val outMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val display = activity.display
            display?.getRealMetrics(outMetrics)
        } else {
            activity.windowManager.defaultDisplay.getMetrics(outMetrics)
        }
        val widthPixels = outMetrics.widthPixels.toFloat()
        val density = outMetrics.density
        val adWidth = (widthPixels / density).toInt()
        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
            activity.applicationContext,
            adWidth
        )
    }

    private fun isTimeToShowFullAd(ctx: Context): Boolean {
        val sp = PreferenceManager.getDefaultSharedPreferences(ctx)
        val diff = System.currentTimeMillis() - adDisplayTime
        val diffInSec = diff / 1000
        return diffInSec >= sp!!.getLong("entry", adsInterVal)
    }

    fun showFullAdOnMainActivity1(activity: Activity, listener: FullAdListener) {
        if (isAdsCheck) {
            count++
            if (count >= counter && isTimeToShowFullAd(activity.applicationContext)) {
                AdsHandler.showInterstitialAds(activity, object : FullAdListener {
                    override fun onComplete(isAdDisplay: Boolean, adNetwork: String) {
                        count = 0
                        listener.onComplete(isAdDisplay, "")
                    }
                })

            } else {
                listener.onComplete(false, "Time up")
            }

        } else {
            listener.onComplete(false, "0")
        }

    }

}