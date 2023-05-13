package id.autodhil.koramvvm.splashscreen

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import id.autodhil.koramvvm.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreenViewModel(application : Application): AndroidViewModel(application) {
    private val waktu_loading = 2000
    private  var sharedPref : SharedPreferences? = null
    private  var editor : SharedPreferences.Editor? = null
    init {
        sharedPref = application.getSharedPreferences("GST",0)
        editor  = sharedPref?.edit()
    }
    fun setupFirebaseAuth(
        navController: NavController,
        context: Context,
        imgKora: ImageView
    ) {
        Glide.with(context)
            .load(getApplication<Application>().getDrawable(R.drawable.koralogo))
            .into(imgKora)

        android.os.Handler().postDelayed({
            val status = getStatus("STT")
            var mAuthStateListener = FirebaseAuth.getInstance().currentUser
            if (mAuthStateListener != null || status == 1) {
                navController.navigate(
                    SplashScreenFragmentDirections.actionSplashScreenFragmentToMainFragment()
                )
            } else {
                navController.navigate(
                    SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()
                )
            }

        }, waktu_loading.toLong())
    }

    private fun getStatus( key : String): Int? {
        return sharedPref?.getInt(key,0)

    }
}

