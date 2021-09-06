package com.lilcode.example.roomdatabasedemo.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lilcode.example.roomdatabasedemo.Product
import com.lilcode.example.roomdatabasedemo.R
import com.lilcode.example.roomdatabasedemo.databinding.MainFragmentBinding
import java.util.*

class MainFragment : Fragment() {

    private var adapter: ProductListAdapter? = null

    companion object {
        fun newInstance() = MainFragment()
    }

    // private lateinit var viewModel: MainViewModel
    val viewModel: MainViewModel by viewModels() // by viewModels()를 사용하면 ViewModelProvider를 사용하지 않고 viewmodel을 지연 생성할 수 있습니다.

    private var _binding: MainFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initListener()
        initObserver()
        initRecycler()
    }

    private fun clearFields() {
        binding.productID.text = ""
        binding.productName.setText("")
        binding.productQuantity.setText("")
    }

    @SuppressLint("SetTextI18n")
    private fun initListener() {
        binding.addButton.setOnClickListener {
            val name = binding.productName.text.toString()
            val quantity = binding.productQuantity.text.toString()

            if (name != "" && quantity != "") {
                val product = Product(name, Integer.parseInt(quantity))
                viewModel.insertProduct(product)
                clearFields()
            } else {
                binding.productID.text = "Incomplete information"
            }
        }

        binding.findButton.setOnClickListener { viewModel.findProduct(binding.productName.text.toString()) }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteProduct(binding.productName.text.toString())
            clearFields()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObserver() {
        viewModel.getAllProducts()?.observe(viewLifecycleOwner, Observer { products ->
            products?.let {
                adapter?.setProductList(it)
            }
        })

        viewModel.getSearchResults().observe(this.viewLifecycleOwner, Observer { products ->
            products?.let {
                if (it.isNotEmpty()) {
                    binding.productID.text = String.format(Locale.US, "%d", it[0].id)
                    binding.productName.setText(it[0].productName)
                    binding.productQuantity.setText(String.format(Locale.US, "%d", it[0].quantity))
                } else {
                    binding.productID.text = "No Match"
                }
            }
        })
    }

    private fun initRecycler() {
        adapter = ProductListAdapter(R.layout.product_list_item)
        binding.productRecycler.layoutManager = LinearLayoutManager(context)
        binding.productRecycler.adapter = adapter
    }

}