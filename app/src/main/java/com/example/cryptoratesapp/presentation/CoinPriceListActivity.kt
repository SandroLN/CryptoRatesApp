package com.example.cryptoratesapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoratesapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoratesapp.domain.CoinInfo
import com.example.cryptoratesapp.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private val binding by lazy{
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        //binding.rvCoinPriceList.itemAnimator = null //if we need turn off animation
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }
}