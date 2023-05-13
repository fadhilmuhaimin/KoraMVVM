package id.autodhil.koramvvm.login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import id.autodhil.koramvvm.R


class LoginViewModel(
    context: Context?,
    val findNavController: NavController
) : ViewModel(){
    private val DOMAIN_NAME: String = "gmail.com"
    var message = MutableLiveData<String>()
    var visibility = MutableLiveData<Boolean>()
    private  var sharedPref : SharedPreferences? = null
    var editor : SharedPreferences.Editor? = null


    init {
        sharedPref = context?.getSharedPreferences("GST",0)
        editor  = sharedPref?.edit()
    }
    fun initView(context: Context,kora:ImageView){
        Glide.with(context)
            .load(context.getDrawable(R.drawable.koralogo))
            .into(kora)
    }




    fun init(email: String, password: String, navController: NavController){
        if(!isEmpty(email)
            &&!isEmpty(password)){
            Log.d("anjay",email+password)
            if (isValidDomain(email)){
                visibility.value = true
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            visibility.value = false
                            navController.navigate(
                                LoginFragmentDirections.actionLoginFragmentToMainFragment()
                            )
                        }else{
                            message.value = "Login gagal, silahkan ulangi"
                            visibility.value = false
                        }
                    }
            }else{
                message.value = "Email tidak valid"
            }
        }else{
            message.value = "Isi semua form"
        }
    }

    fun loginGuest(){
        editor?.putInt("STT",1)
        editor?.commit()
        findNavController.navigate(
            LoginFragmentDirections.actionLoginFragmentToMainFragment()
        )
    }

    private fun isValidDomain(email: String): Boolean {
        val domain = email.substring(email.indexOf("@") + 1).toLowerCase()
        return domain.equals(DOMAIN_NAME)
    }

    private fun isEmpty(string: String): Boolean {
        return string == ""
    }
}