package com.cariad.astudy.kotins

sealed class NetworkResult{
    data class Success(val data: String) : NetworkResult()
    data class Error(val code: Int, val message: String) : NetworkResult()
    object Loading : NetworkResult()
}


fun handleResult(result: NetworkResult) {
    when (result) {
        is NetworkResult.Success -> println("Data: ${result.data}")
        is NetworkResult.Error -> println("Error ${result.code}: ${result.message}")
        NetworkResult.Loading -> println("Loading...")
        // 无需 else，编译器确保覆盖所有子类
    }
}
