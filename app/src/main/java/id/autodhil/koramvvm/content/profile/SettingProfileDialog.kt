package id.autodhil.koramvvm.content.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import id.autodhil.koramvvm.R


class SettingProfileDialog : DialogFragment(){
    private lateinit var nama : EditText
    private lateinit var lokasi : EditText
    private lateinit var btn : Button
    private  var sharedPref : SharedPreferences? = null
    private  var editor : SharedPreferences.Editor? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_profile,container,false)
        if (view != null) {
            nama = view.findViewById(R.id.input_nama)
            lokasi = view.findViewById(R.id.input_wilayah)
            btn = view.findViewById(R.id.btn_oke)

        }
        sharedPref = activity?.getSharedPreferences("TXTE",0)
        nama.hint = getTextnya("TXT")
        lokasi.hint = getTextnyaa("WLY")

        editor  = sharedPref?.edit()



        btn.setOnClickListener {
            val text = nama.text.toString()
            val wilayah = lokasi.text.toString()
            save(text,wilayah)
            dialog?.dismiss()
        }

        return view
    }

    private fun save(text: String, wilayah: String) {
        editor?.putString("TXT",text)
        editor?.putString("WLY",wilayah)
        editor?.commit()
    }
    private fun getTextnya(key: String) : String? {
        return sharedPref?.getString(key,"Nama Anda")

    }private fun getTextnyaa(key: String) : String? {
        return sharedPref?.getString(key,"Wilayah Anda")
    }


}