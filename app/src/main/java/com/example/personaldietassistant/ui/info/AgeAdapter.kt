package com.example.personaldietassistant.ui.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ItemAgeBinding
import com.example.personaldietassistant.model.AgeModel
import com.example.personaldietassistant.util.getColorRes

class AgeAdapter(
    private val canNavigateToNextScreen: (Boolean) -> Unit,
    private val onAgeSelected: (Int) -> Unit
) :
    RecyclerView.Adapter<AgeAdapter.ViewHolder>() {

    private val ageList: IntArray = (18..100).toList().toIntArray()
    private val ageModelList: MutableList<AgeModel> = mutableListOf()

    init {
        for (i in ageList.indices) {
            ageModelList.add(AgeModel(ageList[i], false))
        }
    }

    inner class ViewHolder(val binding: ItemAgeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ageModel: AgeModel) {
            with(binding) {
                tvAge.text = ageModel.age.toString()
                setColor(ivBg, ageModel.isSelected)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemAgeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val ageModel = ageModelList[position]
        viewHolder.bind(ageModel)

        viewHolder.itemView.apply {
            setOnClickListener {

                ageModel.isSelected = !ageModel.isSelected
                resetGiftPackList(position)

                if (ageModel.isSelected) {
                    onAgeSelected.invoke(ageModel.age)
                }
                canNavigateToNextScreen.invoke(ageModel.isSelected)

                val test = ageModelList
                notifyDataSetChanged()
            }

        }
    }

    private fun resetGiftPackList(exceptRow: Int) {
        for (i in 0 until ageModelList.size) {
            if (i != exceptRow) {
                ageModelList[i].isSelected = false
            }
        }
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return ageList.size
    }

    private fun setColor(view: View, isSelected: Boolean) {
        view.apply {
            if (isSelected) setBackgroundColor(view.context.getColorRes(R.color.pumpkin))
            else setBackgroundColor(view.context.getColorRes(R.color.divider))
        }
    }
}