package id.autodhil.koramvvm.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.autodhil.koramvvm.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment() {
    private val viewModel : SplashScreenViewModel by viewModels()
    private lateinit var binding : FragmentSplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentSplashScreenBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        context?.let { viewModel.setupFirebaseAuth(findNavController(), it,binding.imgKora) }
        return binding.root



        


    }

}