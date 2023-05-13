package id.autodhil.koramvvm.content.comic

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import id.autodhil.koramvvm.R
import id.autodhil.koramvvm.databinding.FragmentComicBinding

class ComicFragment : Fragment() {
    var exit = false
    private lateinit var viewModel: ComicViewModel
    private lateinit var binding : FragmentComicBinding
    private var groupAdapter = GroupAdapter<ViewHolder>()

    companion object{
        fun newINstance(): ComicFragment{
            return ComicFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onBackPressed()
        // Inflate the layout for this fragment

        binding = FragmentComicBinding.inflate(inflater,container,false)
        viewModel = ComicViewModel(context,binding.rvKomik,findNavController(),binding.rvKomikk,binding.imageSlider)
        viewModel.visibility.value = true
        viewModel.setRecOnline()
        val listnya = listOf<Int>(R.drawable.banner,R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4)
        var adapter = SliderAdapter(listnya)
        binding.imageSlider.setSliderAdapter(adapter)
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.HORIZONTALFLIPTRANSFORMATION)
        binding.imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
//        binding.imageSlider.autoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        binding.imageSlider.scrollTimeInSec = 4
        binding.imageSlider.startAutoCycle()


        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        context?.let {
            Glide.with(it)
                .load(R.drawable.home)
                .into(binding.toolbar)
        }
        viewModel.visibility.value = false
//        viewModel.initSlider()

        binding.btnGenreRomance.setOnClickListener{
            viewModel.onClickGenre("romantis")
        }

        binding.btnGenreSejarah.setOnClickListener {
            viewModel.onClickGenre("sejarah")
        }

        binding.btnGenreHorror.setOnClickListener{
            viewModel.onClickGenre("horror")
        }

        binding.btnGenreKehidupan.setOnClickListener {
            viewModel.onClickGenre("kehidupan")
        }

        binding.btnGenreKomedi.setOnClickListener {
            viewModel.onClickGenre("komedi")
        }



        return binding.root

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