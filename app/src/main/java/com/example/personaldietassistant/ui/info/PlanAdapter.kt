package com.example.personaldietassistant.ui.info

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
    private val canNavigateToNextScreen: (Boolean) -> Unit,
    private val viewModel: InfoScreenViewModel
) :
    RecyclerView.Adapter<PlanAdapter.ViewHolder>() {
    lateinit var res: Resources

    inner class ViewHolder(val binding: ItemPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(planModel: PlanModel, position: Int) {
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
            if (position == viewModel.validPlanRowId.value) {
                setSelected(viewModel.isPlanValid.value!!)
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
        viewHolder.bind(plan, position)

        viewHolder.itemView.apply {
            setOnClickListener {
                this.context.apply {
                    if (plan.isEnabled) {
                        plan.isSelected = plan.isSelected.not()
                        canNavigateToNextScreen.invoke(plan.isSelected)
                        viewModel.validPlanRowId.value = position
                        viewHolder.setSelected(plan.isSelected)
                    } else {
                        showMessage(
                            viewHolder.itemView,
                            res.getString(R.string.info_plan_unable_message)
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