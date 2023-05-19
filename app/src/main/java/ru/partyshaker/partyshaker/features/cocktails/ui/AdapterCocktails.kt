package ru.partyshaker.partyshaker.features.cocktails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import ru.partyshaker.partyshaker.databinding.CocktailItemBinding
import ru.partyshaker.partyshaker.features.cocktails.data.data_classes.Cocktail

class AdapterCocktailsList : ListAdapter<Cocktail, CocktailsViewHolder>(CocktailItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CocktailItemBinding.inflate(inflater, parent, false)
        return CocktailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.binding.name.text = cocktail.name
        Glide.with(holder.itemView.context).load(cocktail.image).into(holder.binding.imageview)
    }
}