package com.rivskyinc.imageflow.presentation.MainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rivskyinc.imageflow.R
import com.rivskyinc.imageflow.databinding.FragmentMainBinding
import com.rivskyinc.imageflow.presentation.ImageApplication
import com.rivskyinc.imageflow.presentation.adapter.MyListAdapter
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    private lateinit var myAdapter :MyListAdapter
    private val component by lazy{
        (this.requireActivity().application as ImageApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this@MainFragment)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
            binding.recyclerViewMain.apply {
                myAdapter = MyListAdapter()
                adapter = myAdapter
                layoutManager = GridLayoutManager(context, 1)

            }
    }

    companion object {


    }
}