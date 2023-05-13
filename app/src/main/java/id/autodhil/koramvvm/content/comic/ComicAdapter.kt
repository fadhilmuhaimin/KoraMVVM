package id.autodhil.koramvvm.content.comic

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import id.autodhil.koramvvm.content.model.Komik
import id.autodhil.koramvvm.databinding.ItemKomikBinding


class ComicAdapter(
    val status: Int?,
    val list: ArrayList<Komik>,
    val onCLick: (Komik) -> Unit
)
    : RecyclerView.Adapter<ComicAdapter.ViewHolder>(){



    inner class ViewHolder(private var binding : ItemKomikBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(komikData : Komik){
            binding.item = komikData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemKomikBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
     return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        if (status == 1){
            if (position > 1){
                item.sections = "token"
            }
        }
        holder.bind(item)
        holder.itemView.setOnClickListener {
            if (item.sections != "coming"){
                if(item.sections != "token"){
                    onCLick(item)
                }else{
                    Toast.makeText(holder.itemView.context, "Silahkan Login untuk menikmati konten ini", Toast.LENGTH_SHORT).show()
                }
            }else{

            }

        }
    }
}