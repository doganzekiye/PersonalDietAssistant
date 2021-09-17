package com.example.personaldietassistant.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personaldietassistant.databinding.ItemIntroBinding
import com.example.personaldietassistant.model.IntroModel

class IntroPagerAdapter(private val introModelsList: Array<IntroModel> = IntroModel.values()) :
    RecyclerView.Adapter<IntroPagerAdapter.IntroViewHolder>() {

    inner class IntroViewHolder(private val binding: ItemIntroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(introModel: IntroModel) {
            val res = binding.root.context.resources
            with(binding) {
                introTitleTextView.text = res.getString(introModel.title)
                introDescriptionTextView.text = res.getString(introModel.desc)
                introImageView.setImageResource(introModel.image)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IntroPagerAdapter.IntroViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemIntroBinding.inflate(inflater, parent, false)
        return IntroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IntroPagerAdapter.IntroViewHolder, position: Int) {
        holder.bind(introModelsList[position])
    }

    override fun getItemCount(): Int {
        return introModelsList.size
    }


}