package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.currencyconverter.data.MainViewModel
import com.example.currencyconverter.data.utils.Resource
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

            if (binding.etFrom.text.toString().isEmpty()) {
                isEmpty = true
                binding.etFrom.error = "Please input amount"
            }
            if (!isEmpty) {
                val amount = binding.etFrom.text.toString().toDouble()
                val currency1 = binding.spFromCurrency.selectedItem.toString()
                val currency2 = binding.spToCurrency.selectedItem.toString()
                viewModel.setCurrency(
                    amount = amount,
                    currency1 = currency1,
                    currency2 = currency2
                )
                viewModel.getResult().observe(this, {
                    when(it){
                        is Resource.Succes ->{
                            binding.tvResult.text =
                                getString(R.string.result, amount, currency1, it.data, currency2)
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            Toast.makeText(this,"Error occurred : ${it.message}",Toast.LENGTH_LONG).show()
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    }

                })
            }
        }


    }
}