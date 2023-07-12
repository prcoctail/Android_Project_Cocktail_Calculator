package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import ru.partyshaker.partyshaker.databinding.CocktailItemBinding
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail

class CocktailsAdapter(private val listener: Listener) : ListAdapter<Cocktail, CocktailsViewHolder>(
    CocktailsDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CocktailItemBinding.inflate(inflater, parent, false)
        return CocktailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        val itemPos = getItem(position)
        holder.bind(itemPos, listener)
    }

    interface Listener {
        fun onClick(cocktail: Cocktail)
        fun cocktailBannerCloseClick(view: CardView)
    }
}