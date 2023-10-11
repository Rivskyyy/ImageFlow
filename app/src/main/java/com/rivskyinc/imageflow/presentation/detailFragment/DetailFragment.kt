package com.rivskyinc.imageflow.presentation.detailFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.rivskyinc.imageflow.Utils.Const.BASE_IMAGE_URL
import com.rivskyinc.imageflow.databinding.FragmentDetailBinding
import com.rivskyinc.imageflow.domain.entities.Photo
import com.rivskyinc.imageflow.presentation.ImageApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.LinkedList
import javax.inject.Inject

class DetailFragment : Fragment() {

   // private val photoList = listOf<Photo>()
    private var currentPosition: Int = 0

    private var photoList: LinkedList<Photo>? = null

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
          //  getImageUrl?.let { it1 -> photoList.add(it1) }
            getImageTitle = it.getString(TITLE)
            getImageId = it.getString(ID_IMAGE )
            photoList = it.getSerializable(PHOTO_LIST) as LinkedList<Photo>?

            Log.d("Detail", photoList.toString() )
            currentPosition = it.getInt(POSITION)
           // Log.d("Position", currentPosition.toString())
           // Log.d("DetailViewModel", getImageId.toString())

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

        setInfo()

        binding.nextButton.setOnClickListener {
            if (currentPosition < photoList!!.size - 1) {
                currentPosition++
                val nextPhoto = photoList!![currentPosition]
                getImageId = nextPhoto.id
                getImageUrl = BASE_IMAGE_URL + nextPhoto.server + "/" + nextPhoto.id + "_" + nextPhoto.secret + ".jpg"
                getImageTitle = nextPhoto.title

                Log.d("NextButton", getImageUrl.toString())
                setInfo()
                Log.d("NextButton", nextPhoto.id)

            }
        }

        binding.previousButton.setOnClickListener {
            if (currentPosition > 0) {
                currentPosition--
                val previousPhoto = photoList!![currentPosition]
                getImageId = previousPhoto.id
                getImageUrl = BASE_IMAGE_URL + previousPhoto.server + "/" + previousPhoto.id + "_" + previousPhoto.secret + ".jpg"
                getImageTitle = previousPhoto.title
                setInfo()
            }
        }

    }

    private fun setInfo() {

        setImage()
        setDescription()

        viewModel.imageDetail.observe(viewLifecycleOwner){
            binding.tvDescriptionDetail.text = it.photo.description._content
        }
        binding.tvTitleDetail.text= getImageTitle
        binding.progressBar.visibility = View.GONE
    }

    private fun setDescription() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                viewModel.getImageDetail(getImageId ?: throw  RuntimeException("Id == null "))
            }
        }
    }

    private fun setImage() {
        activity?.let { fragmentActivity ->
            Glide.with(fragmentActivity.applicationContext)
                .load(getImageUrl)
                .into(binding.imageViewFragmentDetail)
        }

    }

    companion object {

        private const val URL = "url"
        private const val TITLE = "title"
        private const val ID_IMAGE = "id"
        private const val PHOTO_LIST = "photo_list"
        private const val POSITION = "position"

        @JvmStatic
        fun newInstance(url: String, title: String, id: String, position : Int,
                        photoList: LinkedList<Photo> ) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                    putString(TITLE, title)
                    putString(ID_IMAGE, id)
                    putInt(POSITION,position )
                    putSerializable(PHOTO_LIST, photoList)
                }
            }
    }
}