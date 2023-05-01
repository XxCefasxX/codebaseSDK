package dev.xascar.codebase_sdk.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.cfsproj.code_base_sdk.databinding.FragmentItemsBinding
import dagger.hilt.android.AndroidEntryPoint
import com.cfsproj.code_base_sdk.domain.DomainChar
import com.cfsproj.code_base_sdk.ui.adapter.AppAdapter
import com.cfsproj.code_base_sdk.ui.adapter.CharactersAdapter
import com.cfsproj.code_base_sdk.utils.AppType
import com.cfsproj.code_base_sdk.utils.UIState
import com.cfsproj.code_base_sdk.viewmodel.BaseViewModel
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [ItemsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ItemsFragment : Fragment() {

    private val binding by lazy {
        FragmentItemsBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[BaseViewModel::class.java]
    }

    private lateinit var appType: AppType

    private val appAdapter by lazy{
        AppAdapter{
            viewModel.updateCurrentMatch(it)
            binding.slidingPaneLayout.openPane()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appType = requireActivity().intent.getSerializableExtra("APP_TYPE") as AppType

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.apply {

            svSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchByString(newText)
                    return true
                }

            })

            slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED
            ListDetailsOnBackPressedCallback(slidingPaneLayout).let {
                requireActivity().onBackPressedDispatcher.addCallback(
                    viewLifecycleOwner,
                    it
                )
            }


            lifecycleScope.launch {
                viewModel.characters.collect{
                    when(it){
                        is UIState.Loading -> {
                            Toast.makeText(context,"Loading data",Toast.LENGTH_SHORT).show()
                        }
                        is UIState.Success -> {
                            rvCharacters.apply {

                                val allCharacters = mutableListOf<DomainChar>()


                                layoutManager = LinearLayoutManager(requireContext())
                                adapter = CharactersAdapter{ selectedChar ->
                                    viewModel.updateCurrentMatch(selectedChar)
                                    slidingPaneLayout.open()
                                }.also{ charAdapter ->
                                    charAdapter.submitList(it.data)
                                    allCharacters.clear()
                                    allCharacters.addAll(charAdapter.currentList)

                                    viewModel.stringSearch.observe(viewLifecycleOwner){ searchString ->
                                        charAdapter.submitList(
                                            allCharacters.filter {character ->
                                                StringBuilder().append(character.name).append(character.description).toString().lowercase().contains(searchString.toString().lowercase())
                                            }
                                        )
                                    }
                                }.also {
                                }
                            }
                        }
                        is UIState.Error -> {
                            Toast.makeText(context,"${ it.error}",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }

        viewModel.getCharacters(appType)



        return binding.root
    }

}


class ListDetailsOnBackPressedCallback(private val slidingPaneLayout: SlidingPaneLayout):
    OnBackPressedCallback(slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen), SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }

    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }
}