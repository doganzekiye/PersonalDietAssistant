package com.example.personaldietassistant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personaldietassistant.databinding.ItemPlanBinding
import com.example.personaldietassistant.model.PlanModel

class PlanAdapter(private val planModelsList: Array<PlanModel> = PlanModel.values()) :
    RecyclerView.Adapter<PlanAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(planModel: PlanModel) {
            val res = binding.root.context.resources
            with(binding) {
                planItemTitle.text = res.getString(planModel.title)
                planItemImage.setImageResource(planModel.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanAdapter.ViewHolder {

        val context = parent.context
        //Layout inflater is a class that reads xml view description and converts them to java based View objects.
        val inflater = LayoutInflater.from(context)
        val binding = ItemPlanBinding.inflate(inflater, parent, false)
        // Inflate the custom layout
        // Return a new holder instance
        return ViewHolder(binding)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(planModelsList[position])
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return planModelsList.size

    }
}