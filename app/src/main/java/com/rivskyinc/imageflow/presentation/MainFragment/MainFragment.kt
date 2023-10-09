package com.rivskyinc.imageflow.presentation.MainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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
        (this@MainFragment.activity?.application as ImageApplication).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.progressBar.visibility = View.GONE

        viewModel.imageList.observe( viewLifecycleOwner) {
            if ( it!= null ){
                myAdapter.submitList(it.photos.photo)
            } else {
                binding.progressBar.visibility = View.VISIBLE
                Toast.makeText(this.context, "No Internet connection", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun setupRecyclerView() {
            binding.recyclerViewMain.apply {
                myAdapter = MyListAdapter()
                adapter = myAdapter
                layoutManager = GridLayoutManager(context, 3)
            }
    }

    companion object {


    }
}