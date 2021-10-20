package com.example.personaldietassistant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personaldietassistant.databinding.ItemMealsBinding
import com.example.personaldietassistant.model.MealDescItem

class MealBindingAdapter(private var mealList: MutableList<MealDescItem>) :
    RecyclerView.Adapter<MealBindingAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMealsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealDescItem) {
            with(binding) {
                val setMealItem = MealDescItem(
                    name = item.name,
                    desc = item.desc,
                    carbsCal = item.carbsCal,
                    fatCal = item.fatCal,
                    proteinCal = item.proteinCal
                )
                mealDescItem = setMealItem
                carbsDetail.tvNutrientValue.text = setMealItem.carbsCal.toInt().toString()
                carbsDetail.tvNutrientType.text = "Karbonhidrat"
                fatDetail.tvNutrientValue.text = setMealItem.fatCal.toInt().toString()
                fatDetail.tvNutrientType.text = "Yağ"
                proteinDetail.tvNutrientValue.text = setMealItem.proteinCal.toInt().toString()
                proteinDetail.tvNutrientType.text = "Protein"

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemMealsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val meal: MealDescItem = mealList[position]
        viewHolder.bind(meal)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mealList.size
    }
}
