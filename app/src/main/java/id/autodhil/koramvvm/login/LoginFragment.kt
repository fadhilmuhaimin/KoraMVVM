package id.autodhil.koramvvm.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import id.autodhil.koramvvm.R
import id.autodhil.koramvvm.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    var exit = false
    private lateinit var viewModel : LoginViewModel
    private lateinit var binding : FragmentLoginBinding
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 123
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onBackPressed()
        createRequest()

        mAuth = FirebaseAuth.getInstance()
        binding = FragmentLoginBinding.inflate(inflater)
        viewModel = LoginViewModel(context,findNavController())
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        context?.let { viewModel.initView(it,binding.kora) }
        binding.dafar.setOnClickListener{
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToSignInFragment()
            )
        }
        binding.btnLogin.setOnClickListener {
            viewModel.init(binding.etEmaill.text.toString(),binding.etPasss.text.toString(),findNavController())
        }

        binding.lupa.setOnClickListener {
            val dialog = PasswordResetDialog()
            dialog.show(childFragmentManager,"dialog_password_reset")
        }

        binding.btnGoogle.setOnClickListener {
            viewModel.loginGuest()
//            signIn()
        }



        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account= task.getResult(ApiException::class.java)
                account?.idToken?.let { firebaseAuthWithGoogle(it) }
                Log.d("tess",account?.idToken)
            } catch (e: ApiException) {
                Log.d("tes", e.printStackTrace().toString())
                viewModel.visibility.value = false

            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: String) {
        val credential = GoogleAuthProvider.getCredential(acct, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener() {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = mAuth!!.currentUser
                        viewModel.visibility.value = false
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToMainFragment()
                        )
                    } else {
                        viewModel.visibility.value = false
                    }
                }

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        viewModel.visibility.value = true
    }

    fun createRequest(){
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()


        // Build a GoogleSignInClient with the options specified by gso.


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = context?.let { GoogleSignIn.getClient(it, gso) }
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (exit) {
                    activity?.finish()
                    return
                } else {
                    exit = true
                    Handler().postDelayed({ exit = false }, 2000)
                }
            }
        })
    }

}