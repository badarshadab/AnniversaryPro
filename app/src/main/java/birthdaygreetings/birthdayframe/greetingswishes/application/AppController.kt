package birthdaygreetings.birthdayframe.greetingswishes.application

import com.google.firebase.FirebaseApp
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import com.sm.newadlib.app.LibApplication

//@HiltAndroidApp
class AppController : LibApplication() {


    private val ONESIGNAL_APP_ID = "a4385e89-7468-4851-bf3f-e53d4bf79daf"


//    var allAlbum: HashMap<String, ArrayList<ImageData>>? = null
//    var allFolder: ArrayList<String>? = null
//    var frame: Int = 0
//    var isEditEnable: Boolean = false
//    var isFromSdCardAudio: Boolean = false
//    var minPos: Int = Int.MAX_VALUE
//    private var pvmwsMusicData: MusicData? = null
//    private var second = 3.0f
//    var selectedFolderId: String = ""
//    lateinit var selectedImages: ArrayList<ImageData?>
//    lateinit var selectedImagesstart: ArrayList<ImageData?>
//    var selectedTheme: AllTheme = AllTheme.Mixer
//    lateinit var videoImages: ArrayList<String?>

//    companion object{
//
//        lateinit var startframelist: Array<String>
//        lateinit var endframelist: Array<String>
//        var click: Boolean = false
//        var simpleCache: SimpleCache? = null
//        var VIDEO_HEIGHT: Int = 0
//        var VIDEO_WIDTH: Int = 0
//        var instance: MyApplication? = null
//        var isBreak: Boolean = false
//        var appName: String = ""
//        var pkgName: String = ""
//    }

//    var simpleCache: SimpleCache? = null
//    var exoPlayerCacheSize: Long = (90 * 1024 * 1024).toLong()
//    var leastRecentlyUsedCacheEvictor: LeastRecentlyUsedCacheEvictor? = null
//    var exoDatabaseProvider: ExoDatabaseProvider? = null
//    var context: Context? = null
//    var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null

    override fun onCreate() {
        super.onCreate()

//        Firebase.initialize(this)
        FirebaseApp.initializeApp(this)
        OneSignal.Debug.logLevel = LogLevel.VERBOSE

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)


//        UtilsShareddata.init(this)
//        MyApplication.appName = resources.getString(R.string.app_name)
//
//        MyApplication.pkgName = packageName
//
////        initializeFirebase()
////        Thread { this.firebaseConfig() }.start()
//        try {
//            context = this
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        FirebaseApp.initializeApp(this)
//
//
////        MyApplication.instance = this
//
//        try {
//            MyApplication.startframelist = assets.list("startframe")
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        try {
//            MyApplication.endframelist = assets.list("endframe")
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//
//    }
//
//    private fun init() {
//        if (!PermissionModelUtil(this).needPermissionCheck()) {
//            getFolderList()
//            if (!FileUtils.appDirectory.exists()) {
//                FileUtils.appDirectory.mkdirs()
//            }
//        }
//        try {
//            setVideoHeightWidth()
//        } catch (ex: java.lang.Exception) {
//            ex.printStackTrace()
//        }
//    }
//
//
//    private fun setVideoHeightWidth() {
//        val s = resources.getStringArray(R.array.video_height_width)[EPreferences.getInstance(
//            applicationContext
//        ).getInt(EPreferences.PREF_KEY_VIDEO_QUALITY, 2)]
//        val sb = StringBuilder()
//        sb.append("Application VideoQuality value  is:- ")
//        sb.append(s)
//        Log.d("TAG", sb.toString())
//    }
//
//
//    fun loadBitmapFromAssets(context: Context, path: String?): Bitmap? {
//        var stream: InputStream? = null
//        try {
//            stream = context.assets.open(path!!)
//            return BitmapFactory.decodeStream(stream)
//        } catch (ignored: java.lang.Exception) {
//        } finally {
//            try {
//                stream?.close()
//            } catch (ignored: java.lang.Exception) {
//            }
//        }
//        return null
//    }
//
//    fun getAllAlbum(): java.util.HashMap<String, java.util.ArrayList<ImageData>>? {
//        return this.allAlbum
//    }
//
//
//    fun getCurrentTheme(): String? {
//        return getSharedPreferences("theme", 0).getString(
//            "current_theme",
//            AllTheme.Mixer.toString()
//        )
//    }
//
//    @SuppressLint("Range")
//    fun getFolderList() {
//        this.allFolder = ArrayList<String>()
//        this.allAlbum = HashMap()
//        val query = contentResolver.query(
//            Images.Media.EXTERNAL_CONTENT_URI,
//            arrayOf("_data", "_id", "bucket_display_name", "bucket_id", "datetaken", "_data"),
//            null as String?,
//            null as Array<String?>?,
//            "_data DESC"
//        )
//        if (query!!.moveToFirst()) {
//            val columnIndex = query.getColumnIndex("bucket_display_name")
//            val columnIndex2 = query.getColumnIndex("bucket_id")
//            setSelectedFolderId(query.getString(columnIndex2))
//            do {
//                val pvmwsImageData = ImageData()
//                pvmwsImageData.imagePath = query.getString(query.getColumnIndex("_data"))
//                pvmwsImageData.imageThumbnail = query.getString(query.getColumnIndex("_data"))
//                if (!pvmwsImageData.imagePath.endsWith(".gif")) {
//                    val string = query.getString(columnIndex)
//                    val string2 = query.getString(columnIndex2)
//                    if (!allFolder!!.contains(string2)) {
//                        allFolder!!.add(string2)
//                    }
//                    var list: java.util.ArrayList<ImageData>? =
//                        allAlbum!!.get(string2) as java.util.ArrayList<ImageData>?
//                    if (list == null) {
//                        list = java.util.ArrayList<ImageData>()
//                    }
//                    pvmwsImageData.folderName = string
//                    list.add(pvmwsImageData)
//                    allAlbum!![string2] = list
//                }
//            } while (query.moveToNext())
//        }
//    }
//
//    fun getFrame(): Int {
//        return this.frame
//    }
//
//    fun getMusicData(): MusicData? {
//        return this.pvmwsMusicData
//    }
//
//
//    fun getSecond(): Float {
//        return this.second
//    }
//
//
//    fun getSelectedImages(): java.util.ArrayList<ImageData?> {
//        return this.selectedImages
//    }
//
//    fun getSelectedImagesstart(): java.util.ArrayList<ImageData?> {
//        return this.selectedImagesstart
//    }
//
//
//    fun initArray() {
//        this.videoImages = ArrayList<String?>()
//    }
//
//
//    fun setCurrentTheme(s: String?) {
//        val edit = getSharedPreferences("theme", 0).edit()
//        edit.putString("current_theme", s)
//        edit.commit()
//    }
//
//    fun setFrame(frame: Int) {
//        this.frame = frame
//    }
//
//    fun setMusicData(pvmwsMusicData: MusicData?) {
//        this.pvmwsMusicData = pvmwsMusicData
//    }
//
//
//    fun setSecond(second: Float) {
//        this.second = second
//    }
//
//    fun setSelectedFolderId(selectedFolderId: String?) {
//        this.selectedFolderId = selectedFolderId!!
//    }
//
//
//    fun firebaseConfig() {
//        try {
//            mFirebaseRemoteConfig!!.fetchAndActivate()
//                .addOnCompleteListener { task: Task<Boolean?> ->
//                    this.setupFirebaseConfig(
//                        task
//                    )
//                }
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
//    }
//
//
//    fun initializeFirebase() {
//        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
//        mFirebaseRemoteConfig!!.setConfigSettingsAsync(
//            FirebaseRemoteConfigSettings.Builder().build()
//        )
//    }
//
//    fun cacheClear() {
//        try {
//            val dir = cacheDir
//            deleteDir(dir)
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
//        System.runFinalization()
//        Runtime.getRuntime().gc()
//        System.gc()
//    }
//
//
//    fun setupFirebaseConfig(task: Task<*>) {
//        if (task.isSuccessful) {
//            val string =
//                mFirebaseRemoteConfig!!.getString(resources.getString(R.string.Kotlins_api))
//            val string2 =
//                mFirebaseRemoteConfig!!.getString(resources.getString(R.string.Kotlins_key))
//            if (TextUtils.isEmpty(UtilsShareddata.get(Util_StringShared.MYGST_API))) {
//                UtilsShareddata.set(Util_StringShared.MYGST_API, string)
//            }
//            if (TextUtils.isEmpty(UtilsShareddata.get(Util_StringShared.MYGST_KEY))) {
//                UtilsShareddata.set(Util_StringShared.MYGST_KEY, string2)
//            }
//        }
//
//
//        if (leastRecentlyUsedCacheEvictor == null) {
//            leastRecentlyUsedCacheEvictor = LeastRecentlyUsedCacheEvictor(exoPlayerCacheSize)
//        }
//
//        if (exoDatabaseProvider != null) {
//            exoDatabaseProvider = ExoDatabaseProvider(this)
//        }
//
//        if (MyApplication.simpleCache == null) {
//            MyApplication.simpleCache = SimpleCache(
//                cacheDir,
//                leastRecentlyUsedCacheEvictor!!, exoDatabaseProvider!!
//            )
//            if (MyApplication.simpleCache.cacheSpace >= 400207768) {
//                cacheClear()
//            }
//            Log.i("TAG", "onCreate: " + MyApplication.simpleCache.cacheSpace)
//        }
//    }
//
//    fun deleteDir(dir: File?): Boolean {
//        if (dir != null && dir.isDirectory) {
//            val children = dir.list()
//            for (i in children.indices) {
//                val success = deleteDir(File(dir, children[i]))
//                if (!success) {
//                    return false
//                }
//            }
//            return dir.delete()
//        } else if (dir != null && dir.isFile) {
//            return dir.delete()
//        } else {
//            return false
//        }
    }
}