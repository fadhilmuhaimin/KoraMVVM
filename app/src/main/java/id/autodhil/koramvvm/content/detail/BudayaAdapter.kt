package id.autodhil.koramvvm.content.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import id.autodhil.koramvvm.content.model.Budaya
import id.autodhil.koramvvm.databinding.ListBudayaBinding

class BudayaAdapter (
    private var list : ArrayList<Budaya>,
    private var context: Context
) : RecyclerView.Adapter<BudayaAdapter.ViewHolder>(){
    class ViewHolder(private var binding : ListBudayaBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Budaya){
            binding.item = data
            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListBudayaBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }
}