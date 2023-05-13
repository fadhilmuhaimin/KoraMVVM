package id.autodhil.koramvvm.content.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

import id.autodhil.koramvvm.content.model.Ep
import id.autodhil.koramvvm.content.read.ReadActivity
import id.autodhil.koramvvm.databinding.EpItemBinding


class DetailAdapter(
    private var list: ArrayList<Ep>?,
    private val navController: NavController,
    private val context: Context?
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>(){
    class ViewHolder(private var binding: EpItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(ep_data : Ep?){
            binding.item = ep_data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = EpItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position))
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ReadActivity::class.java).apply {
                putExtra("image",list?.get(position)?.story)
            }
            context?.startActivity(intent)
        }
    }
}