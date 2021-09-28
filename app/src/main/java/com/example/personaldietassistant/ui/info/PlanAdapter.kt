package com.example.personaldietassistant.ui.info

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ItemPlanBinding
import com.example.personaldietassistant.model.PlanModel
import com.example.personaldietassistant.util.showMessage

class PlanAdapter(
    private val planModelsList: Array<PlanModel> = PlanModel.values(),
    private val canNavigateToNextScreen: (Boolean) -> Unit
) :
    RecyclerView.Adapter<PlanAdapter.ViewHolder>() {
    lateinit var res : Resources

    inner class ViewHolder(val binding: ItemPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun bind(planModel: PlanModel) {
            res = binding.root.context.resources
            with(binding) {
                planItemTitle.text = res.getString(planModel.title)
                planItemImage.setImageResource(planModel.image)
                if (planModel.isEnabled) {
                    cardView.setBackgroundResource(R.color.white)
                } else {
                    cardView.setBackgroundResource(R.color.gray)
                    planItemTitle.setTextColor(ContextCompat.getColor(root.context,R.color.gray_dark))
                    //planItemImage.setBackgroundResource(R.color.gray)
                }
            }
        }

        fun setSelected(isSelected: Boolean) {
            if (isSelected) {
                binding.cardView.setBackgroundResource(R.drawable.custom_green_rounded_corners)
            } else {
                binding.cardView.setBackgroundResource(R.drawable.custom_gray_rounded_corners)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

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
                        showMessage(viewHolder.itemView, res.getString(R.string.plan_unable_message))
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