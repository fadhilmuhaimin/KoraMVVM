package id.autodhil.koramvvm.content.detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koramvvm.db.FavoriteHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.autodhil.koramvvm.Constant
import id.autodhil.koramvvm.content.model.Budaya
import id.autodhil.koramvvm.content.model.Ep
import id.autodhil.koramvvm.content.model.Komik
import java.util.HashMap

class DetailViewModel(
    val data: Komik?,
    val rvEpisode: RecyclerView,
    val nacController: NavController,
    val context: Context?,
    val rvBufaya: RecyclerView
) : ViewModel() {
    var listEp = ArrayList<Ep>()
    var listBudaya = ArrayList<Budaya>()
    private var favoriteHelper = context?.let { FavoriteHelper.getInstance(it) }
    private lateinit var komik: Komik
    private var listKomik: ArrayList<Komik>? = ArrayList<Komik>()
    var check = MutableLiveData<Boolean>()

    var visibility = MutableLiveData<Boolean>()
    var visibility1 = MutableLiveData<Boolean>()

    init {
        visibility.value = true
//        if (visibility.value == true) {
//            visibility1.value == false
//        } else {
//            visibility1.value = true
//        }

        check.value = false
        val reference = FirebaseDatabase.getInstance().reference
        val query =
            reference.child(Constant.dbnode_episode)
                .child(data?.episode.toString())


        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("error", error.toString())
            }
            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (singleSnapshot in datasnapshot.children) {
                    val objectMap: Map<String, Object>? =
                        singleSnapshot.value as HashMap<String, Object>
                    var ep = Ep(
                        objectMap?.get(Constant.field_image).toString(),
                        objectMap?.get(Constant.field_title).toString(),
                        objectMap?.get(Constant.field_story).toString(),
                        objectMap?.get(Constant.field_date).toString()
                    )

                    listEp.add(ep)
                }
                Log.d("listnya", listEp.toString())
                setupList()
            }
        })
    }

    private fun setupList() {
        val adapterr = DetailAdapter(listEp, nacController, context)
        rvEpisode.apply {
            rvEpisode.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterr
        }
    }

    private fun setupBudayaList() {
        val adapterr = context?.let { BudayaAdapter(listBudaya, it) }
        rvBufaya.apply {
            rvBufaya.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterr
        }
    }

    fun initBudaya() {
        val reference = FirebaseDatabase.getInstance().reference
        val query =
            reference.child(Constant.dbnode_budaya)
                .child(data?.episode.toString())


        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("error", error.toString())
            }

            override fun onDataChange(datasnapshot: DataSnapshot) {
                for (singleSnapshot in datasnapshot.children) {
                    val objectMap: Map<String, Object>? =
                        singleSnapshot.value as HashMap<String, Object>
                    var ep = Budaya(
                        objectMap?.get(Constant.field_image).toString(),
                        objectMap?.get(Constant.field_city).toString(),
                        objectMap?.get(Constant.field_title).toString(),
                        objectMap?.get(Constant.field_desc).toString()
                    )

                    listBudaya.add(ep)
                }
                Log.d("listnya", listEp.toString())
                setupBudayaList()
            }
        })
    }

    fun setCheckbox() {
        listKomik = favoriteHelper?.getAllKomik()
        if (listKomik?.size != null) {
            if (listKomik?.size!! > 0) {
                var i = 0;
                while (i < listKomik!!.size) {
                    if (listKomik!!.get(i).title.equals(data?.title)) {
                        check.value = true
                    }
                    i++
                }
            }
        }
    }


    fun isChecked() {
        listKomik = favoriteHelper?.getAllKomik()
        var noSimiliarItem = true
        if (listKomik?.size != null) {
            if (listKomik?.size!! > 0) {
                var i = 0;
                while (i < listKomik!!.size) {
                    if (listKomik!!.get(i).title.equals(data?.title)) {
                        noSimiliarItem = false
                        break
                    }
                    i++
                }
            }
            if (noSimiliarItem) {
                komik = Komik()
                komik.title = data?.title
                komik.rate = data?.rate
                komik.image = data?.image
                komik.genre = data?.genre
                komik.episode = data?.episode
                komik.sinopsis = data?.sinopsis
                komik.banner = data?.banner
                val result = favoriteHelper?.insertMovie(komik)
                check.value = true
                if (result != null) {
                    if (result > 0) {
                        komik.id = result.toInt()
                    } else {
                        // Snackbar
                    }
                }

            }

        }
//        }

    }

    fun notCheck() {
        check.value = false
        listKomik = favoriteHelper?.getAllKomik()
        var i = 0
        var id = 0
        if (listKomik?.size != null) {
            while (i < listKomik!!.size) {
                if (listKomik!!.get(i).title.equals(data?.title)) {
                    if (listKomik?.get(i)?.id != null) {
                        id = listKomik!!.get(i).id
                    }
                }
                i++
            }
            favoriteHelper?.delete(id)
        }
    }

}