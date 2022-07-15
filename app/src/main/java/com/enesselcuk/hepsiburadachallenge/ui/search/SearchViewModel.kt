package com.enesselcuk.hepsiburadachallenge.ui.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesselcuk.hepsiburadachallenge.data.domain.ItunesRepos
import com.enesselcuk.hepsiburadachallenge.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val musicRepos: ItunesRepos
) : ViewModel() {

    private val _uiState: MutableStateFlow<ItunesUiState> =
        MutableStateFlow(ItunesUiState(isLoading = false))
    val uiState: StateFlow<ItunesUiState> get() = _uiState

    private var job: Job? = null

    fun fetchSearch(name: String? = null, limit: String? = null, entity: String? = null) {
        job?.cancel()
        job = viewModelScope.launch {
            musicRepos.searchList(name, limit, entity)
                .collect { networkResult ->
                    when (networkResult) {
                        is NetworkResult.Loading -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = true,
                                    isError = false,
                                    result = networkResult.data?.results ?: emptyList()
                                )
                            }
                        }
                        is NetworkResult.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = false,
                                    result = networkResult.data?.results ?: emptyList()
                                )
                            }
                        }
                        is NetworkResult.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = true,
                                    result = networkResult.data?.results ?: emptyList()
                                )
                            }
                        }
                    }
                }
        }
    }

}

