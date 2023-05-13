package id.autodhil.koramvvm.content.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import id.autodhil.koramvvm.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    companion object{
        fun newInstance() : FavoriteFragment{
            return FavoriteFragment()
        }
    }
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding : FragmentFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentFavoriteBinding.inflate(inflater,container,false)
        viewModel = FavoriteViewModel(context,binding.rvFavorite,findNavController())

        binding.setLifecycleOwner(this)
        viewModel.setRecyclerView()

        return binding.root
    }

}