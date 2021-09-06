package com.lilcode.example.roomdatabasedemo.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel // application context 를 사용하기 위해 해당 클래스 사용.
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lilcode.example.roomdatabasedemo.Product
import com.lilcode.example.roomdatabasedemo.ProductRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository = ProductRepository(application)
    private val allProducts: LiveData<List<Product>>?
    private val searchResult: MutableLiveData<List<Product>>

    init {
        allProducts = repository.allProducts
        searchResult = repository.searchResult
    }

    fun insertProduct(product: Product) {
        repository.insertProduct(product)
    }

    fun findProduct(name: String) {
        repository.findProduct(name)
    }

    fun deleteProduct(name: String) {
        repository.deleteProduct(name)
    }

    fun getSearchResults(): MutableLiveData<List<Product>> {
        return searchResult
    }

    fun getAllProducts(): LiveData<List<Product>>? {
        return allProducts
    }
}