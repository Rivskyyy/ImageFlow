package com.rivskyinc.imageflow.presentation.mainFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rivskyinc.imageflow.R
import com.rivskyinc.imageflow.Utils.Const.BASE_IMAGE_URL
import com.rivskyinc.imageflow.databinding.FragmentMainBinding
import com.rivskyinc.imageflow.presentation.ImageApplication
import com.rivskyinc.imageflow.presentation.adapter.MyListAdapter
import com.rivskyinc.imageflow.presentation.detailFragment.DetailFragment
import java.util.LinkedList
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private var listOfId = listOf<com.rivskyinc.imageflow.domain.entities.Photo>()

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    private lateinit var myAdapter: MyListAdapter

    private val component by lazy {
        (this@MainFragment.activity?.application as ImageApplication).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadData()
        binding.progressBar.visibility = View.GONE

    }

    private fun loadData() {

        viewModel.imageList.observe(viewLifecycleOwner) {
            if (it != null) {
                myAdapter.submitList(it.photos.photo)
                listOfId = it.photos.photo
                Log.d("MainFragment", listOfId.toString())
            } else {
                binding.progressBar.visibility = View.VISIBLE
                Toast.makeText(this.context, "No Internet connection", Toast.LENGTH_LONG).show()
            }

        }

    }
    private fun launchDetailFragment(detailFragment: DetailFragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(
                R.id.main_container,
                detailFragment
            )
            .addToBackStack(null)
            .commit()

    }


    private fun setupRecyclerView() {
        binding.recyclerViewMain.apply {
            myAdapter = MyListAdapter { photo, position ->
                val detailFragment = DetailFragment.newInstance(
                    BASE_IMAGE_URL + photo.server + "/" + photo.id + "_" + photo.secret + "_z.jpg",
                    photo.title,
                    photo.id,
                    position,
                    LinkedList(myAdapter.currentList)
                )
                launchDetailFragment(detailFragment)
            }
            adapter = myAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }




}