package com.example.cryptoratesapp.domain

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}