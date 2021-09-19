package com.example.personaldietassistant.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ItemInfoPlanBinding
import com.example.personaldietassistant.model.PlanModel
import com.example.personaldietassistant.util.showMessage

class PlanAdapter(private val planModelsList: Array<PlanModel> = PlanModel.values(), private val canNavigateToNextScreen: (Boolean) -> Unit) :
    RecyclerView.Adapter<PlanAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemInfoPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(planModel: PlanModel) {
            val res = binding.root.context.resources
            with(binding) {
                planItemTitle.text = res.getString(planModel.title)
                planItemImage.setImageResource(planModel.image)
                if (planModel.isEnabled) {
                    planLockLayer.visibility = View.GONE
                } else {
                    planLockLayer.visibility = View.VISIBLE
                }
            }
        }

        fun setSelected(isSelected: Boolean) {
            if (isSelected) {
                binding.cardView.strokeWidth = 2
                binding.cardView.strokeColor = ContextCompat.getColor(binding.root.context, R.color.pumpkin)
            } else {
                binding.cardView.strokeWidth = 0
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanAdapter.ViewHolder {

        val context = parent.context
        //Layout inflater is a class that reads xml view description and converts them to java based View objects.
        val inflater = LayoutInflater.from(context)
        val binding = ItemInfoPlanBinding.inflate(inflater, parent, false)
        // Inflate the custom layout
        // Return a new holder instance
        return ViewHolder(binding)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val plan = planModelsList[position]
        viewHolder.bind(plan)

        viewHolder.itemView.apply {
            setOnClickListener {
                this.context.apply {
                    if (plan.isEnabled) {
                        plan.isSelected = plan.isSelected.not()
                        canNavigateToNextScreen.invoke(plan.isSelected)
                        viewHolder.setSelected(plan.isSelected)
                    } else {
                        showMessage(viewHolder.itemView,"This feature will be enabled soon!")
                    }
                }
            }
        }
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return planModelsList.size
    }

}