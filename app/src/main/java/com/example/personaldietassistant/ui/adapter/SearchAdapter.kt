package com.example.personaldietassistant.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.personaldietassistant.R
import com.example.personaldietassistant.model.foodNutrientsRequest.NutrientsIngredient
import com.example.personaldietassistant.model.foodSearch.Hint
import com.example.personaldietassistant.ui.FoodNutrientsActivity

class SearchAdapter(private var foodHintList: MutableList<Hint>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName = itemView.findViewById<TextView>(R.id.foodName)
        val foodCal = itemView.findViewById<TextView>(R.id.foodCal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val context = parent.context
        //Layout inflater is a class that reads xml view description and converts them to java based View objects.
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val foodView = inflater.inflate(R.layout.item_food, parent, false)
        // Return a new holder instance
        return ViewHolder(foodView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val foodHint: Hint = foodHintList[position]
        // Set item views based on your views and data model
        val foodNameTextView = viewHolder.foodName
        foodNameTextView.text = foodHint.food.label
        val foodCalTextView = viewHolder.foodCal
        foodCalTextView.text = String.format("%.2f", foodHint.food.nutrients.ENERC_KCAL)

        viewHolder.itemView.setOnClickListener {
            val selectedItem = NutrientsIngredient(
                foodId = foodHint.food.foodId,
                measureURI = foodHint.measures[0].uri
            )
            val context = viewHolder.foodName.context
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