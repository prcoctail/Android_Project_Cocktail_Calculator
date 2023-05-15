package ru.partyshaker.partyshaker.features.cocktails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.partyshaker.partyshaker.databinding.CocktailItemBinding
import ru.partyshaker.partyshaker.features.cocktails.data.data_classes.Cocktail

class AdapterCocktailsList : ListAdapter<Cocktail, CocktailsViewHolder>(CocktailItemDiffCallback()) {

//    var cocktails = mutableListOf<Cocktail>()
//    fun setCocktailsList(cocktails: List<Cocktail>) {
//        this.cocktails = cocktails.toMutableList()
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CocktailItemBinding.inflate(inflater, parent, false)
        println("ADAPTER IS WORKING==================")
        return CocktailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.binding.name.text = cocktail.name
        Glide.with(holder.itemView.context).load(cocktail.image).into(holder.binding.imageview)
    }

//    override fun getItemCount(): Int {
//        return cocktails.size
//    }
}

class CocktailsViewHolder(val binding: CocktailItemBinding) :
    RecyclerView.ViewHolder(binding.root) {}