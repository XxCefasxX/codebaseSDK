package dev.xascar.codebase_sdk.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.cfsproj.code_base_sdk.R
import com.cfsproj.code_base_sdk.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import com.cfsproj.code_base_sdk.viewmodel.BaseViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {


    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[BaseViewModel::class.java]
    }

    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.currentCharacter.observe(viewLifecycleOwner){
            binding.apply {
                it?.let {
                    tvCharacterDescription.text = it.description
                    tvCharacterName.text = it.name
                }
                Glide
                    .with(this@DetailsFragment)
                    .load(it.imageUrl)
                    .placeholder(R.drawable.baseline_person_24)
                    .into(binding.ivCharacter)
            }
        }

        return binding.root
    }

}