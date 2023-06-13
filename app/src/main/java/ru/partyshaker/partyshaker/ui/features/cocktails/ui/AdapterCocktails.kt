package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import ru.partyshaker.partyshaker.databinding.CocktailItemBinding
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail

class AdapterCocktailsList : ListAdapter<ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail, ru.partyshaker.partyshaker.ui.features.cocktails.ui.CocktailsViewHolder>(
    ru.partyshaker.partyshaker.ui.features.cocktails.ui.CocktailItemDiffCallback()
) {

    init {
        println("IMAGE URL")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ru.partyshaker.partyshaker.ui.features.cocktails.ui.CocktailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CocktailItemBinding.inflate(inflater, parent, false)
        return ru.partyshaker.partyshaker.ui.features.cocktails.ui.CocktailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ru.partyshaker.partyshaker.ui.features.cocktails.ui.CocktailsViewHolder, position: Int) {
        val cocktail = getItem(position)
        holder.binding.name.text = cocktail.name
        Glide.with(holder.itemView.context).load(cocktail.images[0].smallImage).into(holder.binding.imageview)
        println("IMAGE URL ${cocktail.images[0].smallImage}")
    }
}