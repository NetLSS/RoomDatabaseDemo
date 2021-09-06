package com.lilcode.example.roomdatabasedemo.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.lilcode.example.roomdatabasedemo.Product
import com.lilcode.example.roomdatabasedemo.R
import com.lilcode.example.roomdatabasedemo.databinding.MainFragmentBinding

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
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

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

    private fun initObserver() {
        TODO("Not yet implemented")
    }

    private fun initRecycler() {
        TODO("Not yet implemented")
    }

}