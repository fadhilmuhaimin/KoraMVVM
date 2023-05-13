package id.autodhil.koramvvm.content.comic

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.smarteist.autoimageslider.SliderView
import id.autodhil.koramvvm.Constant
import id.autodhil.koramvvm.R
import id.autodhil.koramvvm.content.detail.DetailFragment
import id.autodhil.koramvvm.content.model.Komik
import kotlinx.coroutines.Job
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ComicViewModel(
    private val context: Context?,
    private val recyclerView: RecyclerView,
    private val navController: NavController,
    val rvKomikk: RecyclerView,
    var sliderView: SliderView


) : ViewModel(){

    val listkomik = ArrayList<Komik>()
    val listkomik1 = ArrayList<Komik>()
    private var vmJob = Job()
    var visibility = MutableLiveData<Boolean>()

    private  var sharedPref : SharedPreferences? = null
    private  var editor : SharedPreferences.Editor? = null

    init {
        sharedPref = context?.getSharedPreferences("GST",0)
        editor  = sharedPref?.edit()
    }


    fun setRecOnline(){


            val reference =
                FirebaseDatabase.getInstance().getReference().child(Constant.dbnode_comic)
//        val reference = FirebaseDatabase.getInstance().getReference().child(Constant.dbnode_comic).orderByChild("genre").equalTo("horror")
            reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (singleSnapshot in dataSnapshot.children) {
                        val objectMap: Map<String, Objects>? =
                            singleSnapshot.value as HashMap<String, Objects>
                        if (objectMap!!.get(Constant.field_sec).toString().equals("1")) {
                            val komik = Komik(
                                objectMap!!.get(Constant.field_episode).toString(),
                                objectMap.get(Constant.genre).toString(),
                                0,
                                objectMap.get(Constant.field_image).toString(),
                                objectMap.get(Constant.field_rate).toString(),
                                objectMap.get(Constant.field_title).toString(),
                                objectMap.get(Constant.field_banner).toString(),
                                objectMap.get(Constant.field_sinop).toString()
                            )
                            listkomik.add(komik)
                        } else if (objectMap!!.get(Constant.field_sec).toString().equals("2")){
                            val komik = Komik(
                                objectMap!!.get(Constant.field_episode).toString(),
                                objectMap.get(Constant.genre).toString(),
                                0,
                                objectMap.get(Constant.field_image).toString(),
                                objectMap.get(Constant.field_rate).toString(),
                                objectMap.get(Constant.field_title).toString(),
                                objectMap.get(Constant.field_banner).toString(),
                                objectMap.get(Constant.field_sinop).toString()
                            )
                            listkomik1.add(komik)
                        }
                    }
                        setupList(listkomik, recyclerView)
                        setupList(listkomik1, rvKomikk)
                }
            })

    }


  private fun setupList(listkomik: ArrayList<Komik>, recyclerVieww: RecyclerView) {
      val status = getStatus("STT")
        val adapterr = ComicAdapter(status,listkomik,{ item : Komik ->onClickItem(item)})
        recyclerVieww.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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

    @SuppressLint("SuspiciousIndentation")
    fun onClickGenre(genre : String){
        val bundle = Bundle()
        val episodeFragment = DetailFragment()
        bundle.putString("genre",genre)
        episodeFragment.arguments = bundle
            navController.navigate(
                R.id.genreFragment,bundle
            )
    }
    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
    private fun getStatus( key : String): Int? {
        return sharedPref?.getInt(key,0)

    }
}