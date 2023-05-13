package id.autodhil.koramvvm.content.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import id.autodhil.koramvvm.content.model.Komik
import id.autodhil.koramvvm.databinding.ItemKomikBinding

class FavoriteAdapter (val list: ArrayList<Komik>,
                       val onCLick: (Komik) -> Unit)
    : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){
    inner class ViewHolder(private var binding : ItemKomikBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(komikData : Komik){
            binding.item = komikData
            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemKomikBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        val item = list.get(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onCLick(item)
        }
    }
}