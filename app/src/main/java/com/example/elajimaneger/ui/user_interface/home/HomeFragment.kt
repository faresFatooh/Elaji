package com.example.elajimaneger.ui.user_interface.home


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elajimaneger.databinding.FragmentHomeBinding



class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategorisAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var itemList: ArrayList<Categories>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupListeners()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        itemList = ArrayList()
        homeViewModel.getItemList().observe(viewLifecycleOwner) { itemList ->
            this.itemList.clear()
            this.itemList.addAll(itemList)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupListeners() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = CategorisAdapter(itemList,requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




