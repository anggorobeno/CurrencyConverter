package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.currencyconverter.data.MainViewModel
import com.example.currencyconverter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






            binding.btnConvertCurrency.setOnClickListener {
                var isEmpty = false

                if (binding.etFrom.text.toString().isEmpty()){
                    isEmpty = true
                    binding.etFrom.error = "Please input amount"
                }
                 if (!isEmpty) {
                    viewModel.setCurrency(
                        amount = binding.etFrom.text.toString().toDouble(),
                        currency1 = binding.spFromCurrency.selectedItem.toString(),
                        currency2 = binding.spToCurrency.selectedItem.toString()
                    )
                    viewModel.getCurrency().observe(this, {
                        val amount = binding.etFrom.text.toString().toDouble()
                        val currency1 = binding.spFromCurrency.selectedItem.toString()
                        val currency2 = binding.spToCurrency.selectedItem.toString()
                        binding.tvResult.text = getString(R.string.result,amount,currency1,it,currency2)
                    })
                }
            }





    }
}