package id.autodhil.koramvvm.content.genre

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.autodhil.koramvvm.Constant
import id.autodhil.koramvvm.R
import id.autodhil.koramvvm.content.comic.ComicAdapter
import id.autodhil.koramvvm.content.detail.DetailFragment
import id.autodhil.koramvvm.content.model.Komik
import java.util.*
import kotlin.collections.HashMap

class GenreViewModel(
    private val context: Context?,
    private val recyclerView: RecyclerView,
    private val navController: NavController,
    private val genre: String?

) : ViewModel(){
    val listkomik = ArrayList<Komik>()
    private  var sharedPref : SharedPreferences? = null
    private  var editor : SharedPreferences.Editor? = null


    init {
        sharedPref = context?.getSharedPreferences("GST",0)
        editor  = sharedPref?.edit()
    }
    fun setRecOnline(){

//        val reference =
//            FirebaseDatabase.getInstance().getReference().child(Constant.dbnode_comic)
        val reference = FirebaseDatabase.getInstance().getReference().child(Constant.dbnode_comic).orderByChild("genre").equalTo(genre)
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (singleSnapshot in dataSnapshot.children) {
                    val objectMap: Map<String, Objects>? =
                        singleSnapshot.value as HashMap<String, Objects>

                        val komik = Komik(
                            objectMap!!.get(Constant.field_episode).toString(),
                            objectMap.get(Constant.genre).toString(),
                            0,
                            objectMap.get(Constant.field_image).toString(),
                            objectMap.get(Constant.field_rate).toString(),
                            objectMap.get(Constant.field_title).toString(),
                            objectMap.get(Constant.field_banner).toString(),
                            objectMap.get(Constant.field_sinop).toString(),
                            objectMap.get(Constant.field_sections).toString()
                        )
                        listkomik.add(komik)

                }

                setupList(listkomik, recyclerView)

            }
        })

    }
    private fun setupList(listkomik: ArrayList<Komik>, recyclerVieww: RecyclerView) {
        val status = getStatus("STT")
        val adapterr = ComicAdapter(status, listkomik, { item : Komik ->onClickItem(item)})
        recyclerVieww.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = adapterr
        }

    }

    private fun onClickItem(komik: Komik){

        val bundle = Bundle()
        val episodeFragment = DetailFragment()
        bundle.putParcelable("komik",komik)
        episodeFragment.arguments = bundle
        navController.navigate(
            R.id.detailFragment,bundle
        )

    }
    private fun getStatus( key : String): Int? {
        return sharedPref?.getInt(key,0)

    }
}