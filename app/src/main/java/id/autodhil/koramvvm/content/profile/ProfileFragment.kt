package id.autodhil.koramvvm.content.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.example.koramvvm.db.FavoriteHelper

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import id.autodhil.koramvvm.R
import id.autodhil.koramvvm.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private  var sharedPref : SharedPreferences? = null
      var editor : SharedPreferences.Editor? = null
    private  var sharedPref1 : SharedPreferences? = null
      var editor1 : SharedPreferences.Editor? = null

    companion object{
        fun newInstance() : ProfileFragment{
            return ProfileFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        viewModel = ProfileViewModel(context,binding.rvComing,findNavController())
        viewModel.setRecOnline()
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = context?.let { GoogleSignIn.getClient(it, gso) }
        initShared()
        val status = getStatus("STT")

        binding.btnSignOut.setOnClickListener {
            if(status == 1){
                editor1?.putInt("STT",0)
                editor1?.commit()
                findNavController().navigate(
                    R.id.loginFragment
                )

            }else{
                signOut()

            }

        }

        binding.tambah.setOnClickListener {
            Toast.makeText(activity, "Maaf, fitur ini akan segera hadir", Toast.LENGTH_SHORT).show()
        }


        binding.name.text = sharedPref?.getString("TXT","Nama Anda")
        binding.lokasi.text = sharedPref?.getString("WLY","Lokasi Anda")
        val favoriteHelper = context?.let { FavoriteHelper.getInstance(it) }
        var test = favoriteHelper?.getAllKomik()

        binding.favor.text = test?.size?.toString()

        binding.btnSetting.setOnClickListener {
            val dialog = SettingProfileDialog()
            activity?.supportFragmentManager?.let { it1 -> dialog.show(it1,getString(R.string.dialog_new)) }
        }

        return binding.root
    }

    private fun getStatus(key: String): Int?{
        return sharedPref1?.getInt(key,0)
    }

    private fun initShared() {
        sharedPref = activity?.getSharedPreferences("TXTE",0)
        editor  = sharedPref?.edit()
        sharedPref1 = activity?.getSharedPreferences("GST",0)
        editor1  = sharedPref1?.edit()
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        mGoogleSignInClient?.signOut()?.addOnCompleteListener(object :OnCompleteListener<Void>{
            override fun onComplete(p0: Task<Void>) {
                findNavController().navigate(
                    R.id.loginFragment

                )
                editor?.putString("TXT","Nama Anda")
                editor?.putString("WLY","Lokasi Anda")
                editor?.commit()
            }

        })
        editor?.putString("TXT","Nama Anda")
        editor?.putString("WLY","Lokasi Anda")
        editor?.commit()
        findNavController().navigate(
            R.id.loginFragment
        )
    }


}