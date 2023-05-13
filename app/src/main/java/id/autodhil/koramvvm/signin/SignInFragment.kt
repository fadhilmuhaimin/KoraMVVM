package id.autodhil.koramvvm.signin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.autodhil.koramvvm.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {
    private lateinit var viewModel: SignInViewModel
    private lateinit var binding: FragmentSignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.btnRegister.setOnClickListener{
            try {
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(requireView().windowToken, 0)
            } catch (e: Exception) {

            }
            viewModel.register(binding.inputEmail.text.toString(),binding.inputPassword.text.toString(),binding.inputConfirmPassword.text.toString(),findNavController())
        }
        return binding.root
    }


}