package ru.partyshaker.partyshaker.ui.features.cocktails.ui.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.CocktailFilterIngredientsBinding
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.FilteredChip

@AndroidEntryPoint
class CocktailsFilterIngredientsFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: CocktailFilterIngredientsBinding
    private val viewModel: CocktailsFilterIngredientsViewModel by activityViewModels()
    private val minimumNumberOfChipsToDisplay = 10

    private lateinit var searchInput: TextInputEditText
    private lateinit var searchApply: ImageButton
    private lateinit var ingredientsChipGroup: ChipGroup
    private lateinit var ingredientsShowMore: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = CocktailFilterIngredientsBinding.inflate(inflater, container, false)
        searchInput = binding.cocktailsFilterSearchInput
        searchApply = binding.cocktailsFilterSearchApplyButton
        ingredientsChipGroup = binding.ingredientsChipGroup
        ingredientsShowMore = binding.ingredientsShowMoreButton

        searchApply.setOnClickListener(this)
        ingredientsShowMore.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.filteredIngredients.observe(viewLifecycleOwner) { ingredients ->
            addChipsToContainers(
                ingredientsChipGroup, ingredients, getString(R.string.ingredients_key)
            )

            setShowMoreButton(ingredientsChipGroup.childCount, ingredientsShowMore)

            setChipGroupHeight(ingredientsChipGroup, ingredientsShowMore)
        }
    }

    private fun addChipsToContainers(
        chipGroupContainer: ChipGroup, chipsContent: MutableList<FilteredChip>, chipGroup: String
    ) {
        if (chipGroupContainer.isNotEmpty()) {
            chipGroupContainer.removeAllViews()
        }

        chipsContent.forEach { content ->
            val chip = createChip(chipGroupContainer, content.name, content.isChecked, chipGroup)
            chipGroupContainer.addView(chip, chipGroupContainer.childCount)
        }
    }

    private fun createChip(
        chipGroupContainer: ChipGroup, chipContent: String, checkedChips: Boolean, chipGroup: String
    ): Chip {
        val chip =
            this.layoutInflater.inflate(
                R.layout.cocktails_filter_item,
                chipGroupContainer,
                false
            ) as Chip

        return chip.apply {
            text = chipContent
            isChecked = checkedChips

            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.update(chipContent, true, chipGroup)
                } else {
                    viewModel.update(chipContent, false, chipGroup)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            searchApply.id -> {
                viewModel.setIngredientsFilter(searchInput.text.toString())
            }

            ingredientsShowMore.id -> {
                setChipGroupHeight(ingredientsChipGroup, ingredientsShowMore)
            }
        }
    }

    private fun setChipGroupHeight(chipGroupContainer: ChipGroup, showMoreButton: Button) {
        if (chipGroupContainer.isNotEmpty()) {
            chipGroupContainer.forEachIndexed { position, chip ->
                if (position > minimumNumberOfChipsToDisplay - 1) {
                    if (chip.visibility == View.VISIBLE) {
                        chip.visibility = View.GONE
                        showMoreButton.text = getString(R.string.filter_show_more_button_unfold)
                    } else {
                        chip.visibility = View.VISIBLE
                        showMoreButton.text = getString(R.string.filter_show_more_button_fold)
                    }
                } else {
                    chip.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setShowMoreButton(childCount: Int, button: Button) {
        if (childCount > minimumNumberOfChipsToDisplay) {
            button.visibility = View.VISIBLE
            button.text = getString(R.string.filter_show_more_button_unfold)
        } else {
            button.visibility = View.GONE
        }
    }
}