package id.autodhil.koramvvm.content.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import id.autodhil.koramvvm.R
import id.autodhil.koramvvm.databinding.FragmentFavoriteBinding


class GenreFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var viewModel: GenreViewModel
    private  var data : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        data = this.arguments?.getString("genre","")
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        viewModel = GenreViewModel(context,binding.rvFavorite,findNavController(),data)

        if (data == "romantis"){
            binding.tvKora.text = "Romantis"
            binding.imgLogo.setImageResource(R.drawable.genre_romance)
        }else if (data == "komedi"){
            binding.tvKora.text = "Komedi"
            binding.imgLogo.setImageResource(R.drawable.genre_comedy)
        }else if (data == "horror"){
            binding.tvKora.text = "Horror"
            binding.imgLogo.setImageResource(R.drawable.genre_horror)
        }else if (data == "sejarah"){
            binding.tvKora.text = "Sejarah"
            binding.imgLogo.setImageResource(R.drawable.genre_history)
        }else if (data == "kehidupan"){
            binding.tvKora.text = "Kehidupan"
            binding.imgLogo.setImageResource(R.drawable.genre_life)
        }
        viewModel.setRecOnline()
        return binding.root
    }
}