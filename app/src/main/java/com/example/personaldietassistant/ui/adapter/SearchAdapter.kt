package com.example.personaldietassistant.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personaldietassistant.databinding.ItemFoodBinding
import com.example.personaldietassistant.model.foodNutrientsRequest.NutrientsIngredient
import com.example.personaldietassistant.model.foodSearch.Hint
import com.example.personaldietassistant.ui.FoodNutrientsActivity

class SearchAdapter(private var foodHintList: MutableList<Hint>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hint) {
            with(binding){
                foodName.text = item.food.label
                foodCal.text =  String.format("%.2f", item.food.nutrients.ENERC_KCAL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {

        val context = parent.context
        //Layout inflater is a class that reads xml view description and converts them to java based View objects.
        val inflater = LayoutInflater.from(context)
        val binding = ItemFoodBinding.inflate(inflater,parent ,false)
        // Inflate the custom layout
        //val foodView = inflater.inflate(R.layout.item_food, parent, false)
        // Return a new holder instance
        return ViewHolder(binding)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val foodHint: Hint = foodHintList[position]
        viewHolder.bind(foodHint)

        viewHolder.itemView.setOnClickListener { //itemView
            val selectedItem = NutrientsIngredient(
                foodId = foodHint.food.foodId,
                measureURI = foodHint.measures[0].uri
            )
            val context = viewHolder.itemView.context
            val intent = Intent(context, FoodNutrientsActivity::class.java)

            intent.putExtra("data", selectedItem)
            context.startActivity(intent)
        }
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return foodHintList.size

    }

    fun filterList(filteredList: List<Hint>) {
        foodHintList.clear()
        foodHintList.addAll(filteredList)
        notifyDataSetChanged()
    }

    fun clearAdapterList() {
        foodHintList.clear()
        notifyDataSetChanged()
    }

}