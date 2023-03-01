package com.example.spacenews.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import androidx.lifecycle.viewModelScope
import com.example.spacenews.SpaceNewsState
import com.example.spacenews.core.Constants
import com.example.spacenews.core.Resource
import com.example.spacenews.domain.GetArticleDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private  val getArticleDetailUseCase: GetArticleDetailUseCase,
    savedStateHandle: SavedStateHandle
) :ViewModel() {

    private val _state = mutableStateOf(SpaceNewsState())
    val state: State<SpaceNewsState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_ID)?.let { id ->
            getArticlesDetails(id)
        }
    }

    private fun getArticlesDetails(id: Int) {
        getArticleDetailUseCase(id).onEach { data ->
            when(data) {
                is Resource.Success -> {
                    _state.value = SpaceNewsState(articlesDetails = data.data)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}