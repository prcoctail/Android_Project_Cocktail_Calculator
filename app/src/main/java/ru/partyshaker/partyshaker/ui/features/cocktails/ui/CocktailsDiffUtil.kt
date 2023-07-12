package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import androidx.recyclerview.widget.DiffUtil
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail

class CocktailsDiffUtil : DiffUtil.ItemCallback<Cocktail>() {
    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem == newItem
    }
}