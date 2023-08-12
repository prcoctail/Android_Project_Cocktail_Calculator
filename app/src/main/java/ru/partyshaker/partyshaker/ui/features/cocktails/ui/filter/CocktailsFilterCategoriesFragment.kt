package ru.partyshaker.partyshaker.ui.features.cocktails.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.forEachIndexed
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.CocktailFilterCategoriesBinding
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.FilteredChip

@AndroidEntryPoint
class CocktailsFilterCategoriesFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: CocktailFilterCategoriesBinding
    private val viewModel: CocktailsFilterCategoriesViewModel by activityViewModels()
    private val minimumNumberOfChipsToDisplay = 10

    private lateinit var complexitiesChipGroup: ChipGroup
    private lateinit var complexitiesShowMore: Button

    private lateinit var strengthsChipGroup: ChipGroup
    private lateinit var strengthsShowMore: Button

    private lateinit var tastesChipGroup: ChipGroup
    private lateinit var tastesShowMore: Button

    private lateinit var formatsChipGroup: ChipGroup
    private lateinit var formatsShowMore: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = CocktailFilterCategoriesBinding.inflate(inflater, container, false)
        complexitiesChipGroup = binding.complexitiesChipGroup
        complexitiesShowMore = binding.complexitiesShowMoreButton

        strengthsChipGroup = binding.strengthsChipGroup
        strengthsShowMore = binding.strengthsShowMoreButton

        tastesChipGroup = binding.tastesChipGroup
        tastesShowMore = binding.tastesShowMoreButton

        formatsChipGroup = binding.formatsChipGroup
        formatsShowMore = binding.formatsShowMoreButton

        complexitiesShowMore.setOnClickListener(this)
        strengthsShowMore.setOnClickListener(this)
        tastesShowMore.setOnClickListener(this)
        formatsShowMore.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.complexities.observe(viewLifecycleOwner) { complexities ->
            addChipsToContainers(
                complexitiesChipGroup, complexities, getString(R.string.complexities_key)
            )

            setShowMoreButton(complexitiesChipGroup.childCount, complexitiesShowMore)

            setChipGroupHeight(complexitiesChipGroup, complexitiesShowMore)
        }

        viewModel.strengths.observe(viewLifecycleOwner) { strengths ->
            addChipsToContainers(
                strengthsChipGroup, strengths, getString(R.string.strengths_key)
            )

            setShowMoreButton(strengthsChipGroup.childCount, strengthsShowMore)

            setChipGroupHeight(strengthsChipGroup, strengthsShowMore)
        }

        viewModel.tastes.observe(viewLifecycleOwner) { tastes ->
            addChipsToContainers(
                tastesChipGroup, tastes, getString(R.string.tastes_key)
            )

            setShowMoreButton(tastesChipGroup.childCount, tastesShowMore)

            setChipGroupHeight(tastesChipGroup, tastesShowMore)
        }

        viewModel.formats.observe(viewLifecycleOwner) { formats ->
            addChipsToContainers(
                formatsChipGroup, formats, getString(R.string.formats_key)
            )

            setShowMoreButton(formatsChipGroup.childCount, formatsShowMore)

            setChipGroupHeight(formatsChipGroup, formatsShowMore)
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
            chipGroupContainer.addView(chip, chipGroupContainer.childCount - 1)
        }
    }

    private fun createChip(
        chipGroupContainer: ChipGroup, chipContent: String, checkedChips: Boolean, chipGroup: String
    ): Chip {
        val chip = this.layoutInflater.inflate(
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
            complexitiesShowMore.id -> {
                setChipGroupHeight(complexitiesChipGroup, complexitiesShowMore)
            }

            strengthsShowMore.id -> {
                setChipGroupHeight(strengthsChipGroup, strengthsShowMore)
            }

            tastesShowMore.id -> {
                setChipGroupHeight(tastesChipGroup, tastesShowMore)
            }

            formatsShowMore.id -> {
                setChipGroupHeight(formatsChipGroup, formatsShowMore)
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