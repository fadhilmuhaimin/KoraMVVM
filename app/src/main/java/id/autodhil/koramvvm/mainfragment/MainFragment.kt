package id.autodhil.koramvvm.mainfragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import id.autodhil.koramvvm.R
import com.google.android.material.bottomnavigation.BottomNavigationView



@Suppress("UNREACHABLE_CODE")
class MainFragment : Fragment() {

    private lateinit var viewPagerr : ViewPager
    var exit = false

    private lateinit var chipNavigationBar: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onBackPressed()
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_main, container, false)
        viewPagerr = v.findViewById<ViewPager>(R.id.fragment_container)
        chipNavigationBar = v.findViewById(R.id.navBar)
        setViewPagerAdapter()
        setBottomNavigation()
        setViewPagerListener()

        return v
    }

    private fun setViewPagerListener() {
        viewPagerr.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> chipNavigationBar.selectedItemId = R.id.bottom_nav_comic
                    1 -> chipNavigationBar.selectedItemId = R.id.bottom_nav_favorite
                    2 -> chipNavigationBar.selectedItemId = R.id.bottom_nav_profile
                }
            }

        })
    }

    private fun setViewPagerAdapter() {
        viewPagerr.adapter = MainPagerAdapter(childFragmentManager)
    }

    private fun setBottomNavigation(){
        chipNavigationBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_comic -> viewPagerr.currentItem = 0
                R.id.bottom_nav_favorite -> viewPagerr.currentItem = 1
                R.id.bottom_nav_profile -> viewPagerr.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener true

        }
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