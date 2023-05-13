package id.autodhil.koramvvm.content.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import id.autodhil.koramvvm.R

import id.autodhil.koramvvm.content.model.Komik
import id.autodhil.koramvvm.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private var data : Komik? = null
    private lateinit var viewModel : DetailViewModel
    private lateinit var binding : FragmentDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater)
        data = this.arguments?.getParcelable<Komik>("komik")
        viewModel = DetailViewModel(data,binding.rvEpisode,findNavController(),context,binding.rvBufaya)
        binding.setLifecycleOwner(this)
        binding.viewModel  = viewModel
        binding.item = data
        context?.let {
            Glide.with(it)
                .load(data?.banner)
                .placeholder(R.drawable.placeholder)
                .into(binding.samulDetail)
        }

        data?.sinopsis?.let { Log.d("haha", it) }
        data?.genre?.let { Log.d("haha", it) }
        viewModel.setCheckbox()
        viewModel.initBudaya()

        binding.checkbox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    viewModel.isChecked()
                }else{
                    viewModel.notCheck()
                }
            }

        })

        binding.btnAbout.setOnClickListener {
            viewModel.visibility1.value = true
            viewModel.visibility.value =false

        }



        binding.btnBack.setOnClickListener {
            viewModel.visibility.value =true
            viewModel.visibility1.value = false
        }

        return binding.root

    }


}