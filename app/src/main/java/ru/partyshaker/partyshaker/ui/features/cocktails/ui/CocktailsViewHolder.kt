package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.partyshaker.partyshaker.databinding.CocktailItemBinding
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail

class CocktailsViewHolder(val view: CocktailItemBinding) :
    RecyclerView.ViewHolder(view.root) {
    private val binding = CocktailItemBinding.bind(view.root)

    fun bind(cocktail: Cocktail, listener: CocktailsAdapter.Listener) = with(binding) {
        cocktailName.text = cocktail.name

        // ПЕРЕДЕЛАТЬ, КОГДА ПОЯВИТСЯ ФЛАГ "IS_TITLE_IMAGE"
        Glide.with(binding.root)
            .load(cocktail.images?.get(0)?.smallImage)
            .into(cocktailImage);

        cocktailIsFavorited.isChecked = cocktail.isFavorited
        //setCocktailIsFavorited(binding.cocktailIsFavorited, cocktail.isFavorited)

        var complexities: String? = null
        cocktail.complexities?.forEach { value ->
            complexities = StringBuilder().append(value.name).toString()
        }

        var tastes: String? = null
        cocktail.tastes?.forEach { value ->
            tastes = StringBuilder().append(value.name).toString()
        }

        var strengths: String? = null
        cocktail.strengths?.forEach { value ->
            strengths = StringBuilder().append(value.name).toString()
        }

        val tags = listOfNotNull(complexities, tastes, strengths)
        cocktailTags.text = tags.toString().removePrefix("[").removeSuffix("]")

        itemView.setOnClickListener {
            listener.onClick(cocktail)
        }
    }

    private fun setCocktailIsFavorited(
        cocktailIsFavoriteButton: ImageButton,
        isFavorited: Boolean
    ) {
//        if (isFavorited) {
//            cocktailIsFavoriteButton
//        }
    }
}