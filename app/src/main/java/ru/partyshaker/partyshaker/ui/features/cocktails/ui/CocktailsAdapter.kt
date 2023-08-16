package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import ru.partyshaker.partyshaker.databinding.CocktailCardListItemBinding
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail


class CocktailsAdapter(private val listener: Listener) : ListAdapter<Cocktail, CocktailsViewHolder>(
    AsyncDifferConfig.Builder(CocktailsDiffUtil()).build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CocktailCardListItemBinding.inflate(inflater, parent, false)
        return CocktailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface Listener {
        fun navigateToCocktailDetails(cocktail: Cocktail)
    }
}