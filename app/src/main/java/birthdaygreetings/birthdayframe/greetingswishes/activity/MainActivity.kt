package birthdaygreetings.birthdayframe.greetingswishes.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import birthdaygreetings.birthdayframe.greetingswishes.R
import birthdaygreetings.birthdayframe.greetingswishes.databinding.ActivityMainBinding
import birthdaygreetings.birthdayframe.greetingswishes.invitationCard.InvitationEditFragment
import birthdaygreetings.birthdayframe.greetingswishes.util.AppUtils
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sm.newadlib.handlers.AdsHandler

class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController
    private lateinit var b: ActivityMainBinding
    private val readExternal= Manifest.permission.READ_EXTERNAL_STORAGE
    private val readVideo= Manifest.permission.READ_MEDIA_VIDEO
    private val readImages= Manifest.permission.READ_MEDIA_IMAGES
    private val readAudio= Manifest.permission.READ_MEDIA_AUDIO
    private val permissions= arrayOf(
        readVideo,readImages,readAudio , readExternal
    )

    companion object {
        lateinit var activity: MainActivity
    }

    private var hasNotificationPermissionGranted = false

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            hasNotificationPermissionGranted = isGranted
            if (!isGranted) {
                if (Build.VERSION.SDK_INT >= 33) {
                    if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                        showNotificationPermissionRationale()
                    } else {
                        showSettingDialog()
                    }
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "notification permission granted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        b = ActivityMainBinding.inflate(layoutInflater)

        checkNotifyPermission()
        setContentView(b.root)

//        AppUtils.setStatusBarBackground(this, window)
//        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setSupportActionBar(b.appBar.includedToolBar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
//        requestPermissions()
        b.navLay.cardView.setBackgroundResource(R.drawable.curved_bg_gradient)
        setupActionBarWithNavController(navController)
        setupToolbar()
        val versionNa = getVersionName(this)
        b.navLay.versionName.text = resources.getString(R.string.version) + " " + versionNa

        activity = this
//        Toast.makeText(this, "Version Name $versionNa", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment)
        val destination = navController.currentDestination
        if (destination?.id == R.id.nav_main) {

            if (b.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                b.drawerLayout.close()
            } else {
                AppUtils.fullExitScreen(this)
            }
        }  else {
            AdsHandler.launchReviewPopup(this, object : AdsHandler.ReviewCallBack {
                override fun onComplete(isSucces: Boolean) {
                }
            })
            if (b.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                b.drawerLayout.close()
            }
            super.onBackPressed()
        }
    }

    fun backButtonClick(v: View) {
        b.drawerLayout.close()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupToolbar() {
        b.appBar.includedToolBar.toolbar.setNavigationOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment)
            val destination = navController.currentDestination
            if (destination?.id == R.id.nav_main) {
                b.drawerLayout.openDrawer(GravityCompat.START)
            } else {
                navController.popBackStack()
            }
        }
    }

    override fun onResume() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.nav_main -> b.appBar.includedToolBar.toolbar.setNavigationIcon(R.drawable.ic_navigation_bar_white)
                else -> b.appBar.includedToolBar.toolbar.setNavigationIcon(R.drawable.ic_back)
            }
        }
        super.onResume()
        setDrawerLock()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun checkNotifyPermission() {
        if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= 33) {
                notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            } else {
                hasNotificationPermissionGranted = true
            }
        } else {
//            Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show()
        }
    }

    private fun showSettingDialog() {
        MaterialAlertDialogBuilder(
            this,
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setTitle("Notification Permission")
            .setMessage("Notification permission is required, Please allow notification permission from setting")
            .setPositiveButton("Ok") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:$packageName")
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showNotificationPermissionRationale() {
        MaterialAlertDialogBuilder(
            this,
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setTitle("Alert")
            .setMessage("Notification permission is required, to show notification")
            .setPositiveButton("Ok") { _, _ ->
                if (Build.VERSION.SDK_INT >= 33) {
                    notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    fun shareApp(v: View) {
        AppUtils.shareApp(this)
        b.drawerLayout.closeDrawer(Gravity.LEFT)
    }

    fun savedItems(v: View) {
        AppUtils.changeFragmentWithPosition(navController, R.id.saved_main_frag, this)
    }

    fun rateus(v: View) {
        AppUtils.rateUs(this)
    }

    fun feedback(v: View) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data =
                Uri.parse("mailto:info.greetingswishes@gmail.com") // Change to your email address
            putExtra(Intent.EXTRA_SUBJECT, "Feedback on Your App")
            putExtra(Intent.EXTRA_TEXT, "Write your feedback here...")
        }
        startActivity(emailIntent)
    }

    fun privacyPolicy(v: View) {
        AppUtils.openUrl(this, getString(R.string.pp_url))
    }

    fun getVersionName(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""
    }

    fun setDrawerLock() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id != R.id.nav_main) {
                b.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

            } else {
                b.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }
    }

    private val videoImagesPermission=registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){permissionMap->
        //Image And Video Permission
        if (permissionMap.all { it.value }){
            Toast.makeText(this, "Media permissions granted", Toast.LENGTH_SHORT).show()
        }else{
//            requestPermissions()
            Toast.makeText(this, "Image And Video permissions not granted!", Toast.LENGTH_SHORT).show()
        }
    }

    //register a permissions activity launcher for a single permission
    private val readExternalPermission=registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted->
        //Audio And Music Permission
        if (isGranted){
            Toast.makeText(this, "Read external storage permission granted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Audio And Music permission denied!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun requestPermissions(){
        //check the API level
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            //filter permissions array in order to get permissions that have not been granted
            val notGrantedPermissions=permissions.filterNot { permission->
                ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED
            }
            if (notGrantedPermissions.isNotEmpty()){
                //check if permission was previously denied and return a boolean value
                val showRationale=notGrantedPermissions.any { permission->
                    shouldShowRequestPermissionRationale(permission)
                }
                //if true, explain to user why granting this permission is important
                if (showRationale){
                    AlertDialog.Builder(this)
                        .setTitle("Storage Permission")
                        .setMessage("Storage permission is needed in order to show images and videos")
                        .setNegativeButton("Cancel"){dialog,_->
                            Toast.makeText(this, "Read media storage permission denied!", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        .setPositiveButton("OK"){_,_->
                            videoImagesPermission.launch(notGrantedPermissions.toTypedArray())
                        }
                        .show()
                }else{
                    //launch the videoPermission ActivityResultContract
                    videoImagesPermission.launch(notGrantedPermissions.toTypedArray())
                }
            }else{
                Toast.makeText(this, "Read media storage permission granted", Toast.LENGTH_SHORT).show()
            }
        } else{
            //check if permission is granted
            if (ContextCompat.checkSelfPermission(this,readExternal) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Read external storage permission granted", Toast.LENGTH_SHORT).show()
            }else{
                if (shouldShowRequestPermissionRationale(readExternal)){
                    AlertDialog.Builder(this)
                        .setTitle("Storage Permission")
                        .setMessage("Storage permission is needed in order to show images and video")
                        .setNegativeButton("Cancel"){dialog,_->
                            Toast.makeText(this, "Read external storage permission denied!", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                        .setPositiveButton("OK"){_,_->
                            readExternalPermission.launch(readExternal)
                        }
                        .show()
                }
                else {
                    readExternalPermission.launch(readExternal)
                }
            }
        }
    }

}