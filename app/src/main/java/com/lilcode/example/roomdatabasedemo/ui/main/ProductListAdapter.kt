package com.lilcode.example.roomdatabasedemo.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lilcode.example.roomdatabasedemo.Product
import com.lilcode.example.roomdatabasedemo.databinding.ProductListItemBinding

class ProductListAdapter(private val productItemLayout: Int) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private var productList: List<Product>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListAdapter.ViewHolder {
        val binding =
            ProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        productList?.let {
            holder.itemBinding.productRow.text = it[position].productName
        }
    }

    override fun getItemCount(): Int {
        return if (productList == null) 0 else productList!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }

    inner class ViewHolder(val itemBinding: ProductListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

    }

}