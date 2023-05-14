package ru.partyshaker.partyshaker.cocktails.data.data_classes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.partyshaker.partyshaker.databinding.CocktailItemBinding

class AdapterCocktailsList: RecyclerView.Adapter<CocktailsViewHolder>() {

    var cocktails = mutableListOf<ru.partyshaker.partyshaker.cocktails.data.data_classes.Result>()
    fun setCocktailsList(cocktails: List<ru.partyshaker.partyshaker.cocktails.data.data_classes.Result>) {
        this.cocktails = cocktails.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CocktailItemBinding.inflate(inflater, parent, false)
        return CocktailsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        val cocktail = cocktails[position]
        holder.binding.name.text = cocktail.name
        Glide.with(holder.itemView.context).load(cocktail.image).into(holder.binding.imageview)
    }
    override fun getItemCount(): Int {
        return cocktails.size
    }
}
class CocktailsViewHolder(val binding: CocktailItemBinding) : RecyclerView.ViewHolder(binding.root) {
}