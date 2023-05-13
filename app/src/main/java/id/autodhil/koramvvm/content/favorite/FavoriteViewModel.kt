package id.autodhil.koramvvm.content.favorite

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koramvvm.db.FavoriteHelper
import id.autodhil.koramvvm.R
import id.autodhil.koramvvm.content.comic.ComicAdapter
import id.autodhil.koramvvm.content.detail.DetailFragment
import id.autodhil.koramvvm.content.model.Komik

class FavoriteViewModel(
    val context: Context?,
    val rvFavorite: RecyclerView,
    val findNavController: NavController
) : ViewModel(){
    private  var sharedPref : SharedPreferences? = null
    private  var editor : SharedPreferences.Editor? = null

    init {
        sharedPref = context?.getSharedPreferences("GST",0)
        editor  = sharedPref?.edit()
    }

    fun setRecyclerView(){
        val status = getStatus("STT")
        val favoriteHelper = context?.let { FavoriteHelper.getInstance(it) }
        val adapterr = favoriteHelper?.getAllKomik()?.let { ComicAdapter(status,
            it,
            { item : Komik ->onClickItem(item)}) }
        rvFavorite.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = adapterr
        }
    }
    private fun onClickItem(komik: Komik){
        val bundle = Bundle()
        val episodeFragment = DetailFragment()
        bundle.putParcelable("komik",komik)
        episodeFragment.arguments = bundle
        findNavController.navigate(
            R.id.detailFragment,bundle
        )
    }

    private fun getStatus( key : String): Int? {
        return sharedPref?.getInt(key,0)

    }

}