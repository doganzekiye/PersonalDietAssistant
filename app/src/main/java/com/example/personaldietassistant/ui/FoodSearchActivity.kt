package com.example.personaldietassistant.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.personaldietassistant.R
import com.example.personaldietassistant.databinding.ActivityFoodSearchBinding
import com.example.personaldietassistant.model.foodSearch.Hint
import com.example.personaldietassistant.ui.adapter.SearchAdapter
import com.example.personaldietassistant.util.show
import com.example.personaldietassistant.util.showMessage

class FoodSearchActivity : AppCompatActivity() {
    lateinit var searchViewModel: FoodSearchViewModel
    lateinit var binding: ActivityFoodSearchBinding
    lateinit var adapter: SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_search)
        searchViewModel = ViewModelProvider(this).get(FoodSearchViewModel::class.java)
        observeChange()
        setListener()
    }

    private fun observeChange() {
        searchViewModel.hintLiveData.observe(this, { hints ->
            setAdapterList(hints)
            showMessage("done")
        })
    }

    private fun setListener() {

        binding.editTextSearchFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                searchViewModel.getFoodSearch(s.toString(), showLoading = {
                    binding.loadingAnimation.show(it)
                }
                )
            }
        })
    }

    private fun setAdapterList(hints: List<Hint>) {
        adapter = SearchAdapter(hints.toMutableList())
        // Attach the adapter to the recyclerview to populate items
        binding.foodSearchRecyclerView.adapter = adapter
    }


}