package com.enesselcuk.hepsiburadachallenge.ui.search

import android.annotation.SuppressLint
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesselcuk.hepsiburadachallenge.data.remote.model.Result
import com.enesselcuk.hepsiburadachallenge.databinding.FragmentSearchBinding
import com.enesselcuk.hepsiburadachallenge.ui.BaseFragment
import com.enesselcuk.hepsiburadachallenge.ui.search.adapter.ItunesAdapter
import com.enesselcuk.hepsiburadachallenge.util.ItunesConstant.QUERY_APPS
import com.enesselcuk.hepsiburadachallenge.util.ItunesConstant.QUERY_BOOKS
import com.enesselcuk.hepsiburadachallenge.util.ItunesConstant.QUERY_MOVIES
import com.enesselcuk.hepsiburadachallenge.util.ItunesConstant.QUERY_MUSIC
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var musicAdapter: ItunesAdapter

    private val arraySearch = ArrayList<Result>()

    override fun definition() {

        musicAdapter = ItunesAdapter(ItunesAdapter.MusicClick {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
            findNavController().navigate(action)
        })

        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        binding.mediaClick = this
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

        musicAdapter.notifyDataSetChanged()

        val arrayMovies = ArrayList<Result>()
        musicAdapter.currentList.forEach {
            viewModel.fetchSearch(name = it.trackName, limit = limit, QUERY_MOVIES)
            arrayMovies.addAll(listOf(it))

        }
        musicAdapter.submitList(arrayMovies)
        searchMusic()
    }

    fun clickMusic() {
        musicAdapter.notifyDataSetChanged()
        val arrayMusic = ArrayList<Result>()
        musicAdapter.currentList.forEach {

            viewModel.fetchSearch(name = it.trackName, limit = limit, QUERY_MUSIC)
            arrayMusic.addAll(listOf(it))

        }
        musicAdapter.submitList(arrayMusic)
        searchMusic()

    }


    fun clickApps() {
        musicAdapter.notifyDataSetChanged()
        val arrayApps = ArrayList<Result>()
        musicAdapter.currentList.forEach {
            viewModel.fetchSearch(name = it.trackName, limit = limit, QUERY_APPS)
            arrayApps.addAll(listOf(it))
        }
        musicAdapter.submitList(arrayApps)
        searchMusic()

    }

    fun clickBooks() {
        musicAdapter.notifyDataSetChanged()
        val arrayBooks = ArrayList<Result>()
        musicAdapter.currentList.forEach {
            viewModel.fetchSearch(name = it.trackName, limit = limit, QUERY_BOOKS)
            arrayBooks.addAll(listOf(it))
        }
        musicAdapter.submitList(arrayBooks)
        searchMusic()
    }

    private fun musicState(musicUiState: ItunesUiState) {
        musicUiState.isLoading.let {
            binding.itunesState = it
        }
        musicUiState.result.let {
            musicAdapter.submitList(it)
            arraySearch.addAll(it)
        }
    }

    private fun searchMusic() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                viewModel.fetchSearch(newText, limit)

                val arrayListSearch = ArrayList<Result>()
                arraySearch.filter { search ->
                    if (newText?.let {
                            search.trackName?.lowercase()?.contains(it.lowercase())
                        } == true) {
                        arrayListSearch.add(search)
                    }
                    true
                }
                musicAdapter.submitList(arrayListSearch)
                musicAdapter.notifyItemChanged(musicAdapter.itemCount)
                return false
            }
        })
    }

    companion object {
        const val limit = "20"
    }

}