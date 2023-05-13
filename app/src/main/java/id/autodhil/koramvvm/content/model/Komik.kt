package id.autodhil.koramvvm.content.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Komik(
    var episode: String? = "",
    var genre: String? ="",
    var id: Int =0,
    var image: String? = "",
    var rate: String?= "",
    var title: String? ="",var banner: String? = "", var sinopsis : String? = "", var sections : String =""
) : Parcelable