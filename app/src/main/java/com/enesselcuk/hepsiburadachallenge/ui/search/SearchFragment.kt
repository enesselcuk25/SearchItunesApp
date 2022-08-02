package com.enesselcuk.hepsiburadachallenge.ui.search

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.enesselcuk.hepsiburadachallenge.common.BaseFragment
import com.enesselcuk.hepsiburadachallenge.data.remote.model.Result
import com.enesselcuk.hepsiburadachallenge.databinding.FragmentSearchBinding
import com.enesselcuk.hepsiburadachallenge.ui.search.adapter.ItunesAdapter
import com.enesselcuk.hepsiburadachallenge.util.ItunesConstant.QUERY_APPS
import com.enesselcuk.hepsiburadachallenge.util.ItunesConstant.QUERY_BOOKS
import com.enesselcuk.hepsiburadachallenge.util.ItunesConstant.QUERY_MOVIES
import com.enesselcuk.hepsiburadachallenge.util.ItunesConstant.QUERY_MUSIC
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var musicAdapter: ItunesAdapter
    override fun definition() {
        musicAdapter = ItunesAdapter(ItunesAdapter.MusicClick(::detailClick))

        with(binding) {
            recyclerView.apply {
                layoutManager =
                    GridLayoutManager(requireContext(), 2)
                adapter = musicAdapter
            }
            mediaClick = this@SearchFragment


            searchView.setIconifiedByDefault(true)
            searchView.isIconified = false
        }
        searchMusic()
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun setObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect {
                        musicState(it)
                    }
                }
            }
        }
    }

    fun clickMovie() {
        viewModel.fetchSearch(
            name = binding.searchView.query.toString(),
            limit = limit,
            QUERY_MOVIES
        )
    }

    fun clickMusic() {
        viewModel.fetchSearch(
            name = binding.searchView.query.toString(),
            limit = limit,
            QUERY_MUSIC
        )
    }

    fun clickApps() {
        viewModel.fetchSearch(
            name = binding.searchView.query.toString(),
            limit = limit,
            QUERY_APPS
        )
    }


    fun clickBooks() {
        viewModel.fetchSearch(
            name = binding.searchView.query.toString(),
            limit = limit,
            QUERY_BOOKS
        )
    }

    private fun musicState(musicUiState: ItunesUiState) {
        musicUiState.isLoading.let {
            binding.itunesState = it
        }
        musicUiState.result.let {
            musicAdapter.submitList(it)

        }
    }

    private fun searchMusic() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.fetchSearch(query, limit)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun detailClick(result: Result) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(result)
        findNavController().navigate(action)
    }

    companion object {
        const val limit = "20"
    }

}