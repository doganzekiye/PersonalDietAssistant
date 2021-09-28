package com.example.personaldietassistant.ui.info

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
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
    lateinit var res: Resources

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
                    planItemTitle.setTextColor(
                        ContextCompat.getColor(
                            root.context,
                            R.color.gray_dark
                        )
                    )
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
        val inflater = LayoutInflater.from(context)
        val binding = ItemPlanBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

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
                        showMessage(
                            viewHolder.itemView,
                            res.getString(R.string.plan_unable_message)
                        )
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return planModelsList.size
    }

}