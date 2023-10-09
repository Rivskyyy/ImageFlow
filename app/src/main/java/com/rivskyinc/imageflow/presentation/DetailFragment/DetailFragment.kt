package com.rivskyinc.imageflow.presentation.DetailFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.rivskyinc.imageflow.databinding.FragmentDetailBinding
import com.rivskyinc.imageflow.presentation.ImageApplication
import com.rivskyinc.imageflow.presentation.MainFragment.MainViewModel
import com.rivskyinc.imageflow.presentation.MainFragment.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding
    private var getImageUrl: String? = null
    private var getImageTitle : String? = null
    private var getImageId : String? = null

    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
    }

    private val component by lazy {
        (this@DetailFragment.activity?.application as ImageApplication).component
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            getImageUrl = it.getString(URL)
            getImageTitle = it.getString(TITLE)
            getImageId = it.getString(ID_IMAGE )
            Log.d("DetailViewModel", getImageId.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.injectInFragmentDetail(this)
        super.onViewCreated(view, savedInstanceState)
        binding.onBackButton.setOnClickListener {
            activity?.onBackPressed()
        }
        setImage()
    }

    private fun setImage() {
        activity?.let { fragmentActivity ->
            Glide.with(fragmentActivity.applicationContext).load(getImageUrl).into(binding.imageViewFragmentDetail)
            binding.tvTitleDetail.text= getImageTitle

            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    viewModel.getImageDetail(getImageId ?: throw  RuntimeException("Id == null "))
                }
            }
            viewModel.imageDetail.observe(viewLifecycleOwner){
                binding.tvDescriptionDetail.text = it.photo.description._content
            }
        }

        binding.progressBar.visibility = View.GONE
    }

    companion object {

        private const val URL = "url"
        private const val TITLE = "title"
        private const val ID_IMAGE = "id"

        @JvmStatic
        fun newInstance(url: String, title : String, id : String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                    putString(TITLE, title)
                    putString(ID_IMAGE, id)
                }
            }
    }
}