package id.autodhil.koramvvm.content.model

import android.os.Parcel
import android.os.Parcelable


data class Ep(var image: String? = "", var title: String?="" , var story: String?, var date : String? = "") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        TODO("story")
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Ep> {
        override fun createFromParcel(parcel: Parcel): Ep {
            return Ep(parcel)
        }

        override fun newArray(size: Int): Array<Ep?> {
            return arrayOfNulls(size)
        }
    }
}