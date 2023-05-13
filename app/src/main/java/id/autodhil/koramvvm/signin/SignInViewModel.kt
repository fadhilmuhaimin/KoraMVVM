package id.autodhil.koramvvm.signin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import id.autodhil.koramvvm.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import id.autodhil.koramvvm.firebase.model.User
import java.lang.Exception
import kotlin.properties.Delegates

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG: String = "RegisterActivity"
    private val DOMAIN_NAME: String = "gmail.com"
    private var auth: FirebaseAuth
    var _email = MutableLiveData<String>()
    var message = MutableLiveData<String>()
    var _pass = MutableLiveData<String>()
    lateinit var usere: FirebaseUser
    var statusLogin by Delegates.notNull<Boolean>()
    var visibility = MutableLiveData<Boolean>()

    init {
        _email.value = ""
        _pass.value = ""
        auth = FirebaseAuth.getInstance()
//        statusLogin = false
    }


    fun register(email: String, password: String, confirmPass: String,navController: NavController) {
        visibility.value = true
        if (!isEmpty(email)
            && !isEmpty(password)
            && !isEmpty(confirmPass)
        ) {
            if (isValidDomain(email)) {
                if (doStringsMatch(password, confirmPass)) {
                    registerNewEmail(email, password,navController)
                }else{
                    message.value = "Password tidak sama"
                    visibility.value = false
                }

            }else{
                message.value = "Email tidak valid"
                visibility.value = false
            }
        }else{
            message.value = "Wajib isi semua form"
            visibility.value = false
        }


    }



    private fun isValidDomain(email: String): Boolean {
        val domain = email.substring(email.indexOf("@") + 1).toLowerCase()
        return domain.equals(DOMAIN_NAME)
    }

    private fun isEmpty(string: String): Boolean {
        return string == ""
    }

    private fun doStringsMatch(s1: String, s2: String): Boolean {
        return s1 == s2
    }

    fun registerNewEmail(email: String, password: String,navController: NavController) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    usere = auth.currentUser!!
                    val user = User(
                        email.substring(0, email.indexOf("@")),
                        "1",
                        "",
                        FirebaseAuth.getInstance().currentUser!!.uid
                    )
                    FirebaseDatabase.getInstance().getReference()
                        .child(getApplication<Application>().getString(R.string.dbnode_users))
                        .child(FirebaseAuth.getInstance().currentUser!!.uid)
                        .setValue(user).addOnCompleteListener(object : OnCompleteListener<Void> {
                            override fun onComplete(p0: Task<Void>) {
                                FirebaseAuth.getInstance().signOut()
                                statusLogin = true
                                visibility.value = false
                                navController(navController)
                                message.value = "Registrasi Berhasil!"
                            }

                        }).addOnFailureListener(object : OnFailureListener {
                            override fun onFailure(p0: Exception) {

                            }

                        })

                    FirebaseAuth.getInstance().signOut()
                } else {
                    // If sign in fails, display a message to the user.
                    message.value = "Registrasi Gagal, Coba Lagi"
                    visibility.value = false
                    statusLogin = false
                }
            }
    }

    fun navController(navController: NavController){
        navController.navigate(
            SignInFragmentDirections.actionSignInFragmentToLoginFragment()
        )
    }
}



