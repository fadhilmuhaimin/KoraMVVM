package id.autodhil.koramvvm.mainfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import id.autodhil.koramvvm.content.comic.ComicFragment
import id.autodhil.koramvvm.content.favorite.FavoriteFragment
import id.autodhil.koramvvm.content.profile.ProfileFragment

@Suppress("DEPRECATION")
class MainPagerAdapter(var fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ComicFragment.newINstance()
            1 -> FavoriteFragment.newInstance()
            2 -> ProfileFragment.newInstance()
            else -> ComicFragment.newINstance()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}