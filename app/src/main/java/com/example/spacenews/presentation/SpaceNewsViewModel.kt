package com.example.spacenews.presentation

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import com.example.spacenews.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import com.example.spacenews.domain.ArticleDetailDomain
import com.example.spacenews.domain.ArticleDomain
import kotlinx.coroutines.flow.onEach
import com.example.spacenews.domain.GetArticlesUseCase

@HiltViewModel
class SpaceNewsViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SpaceNewsState())
    val state: State<SpaceNewsState> = _state

    init {
        getArticles(limit = 50)
    }

    private fun getArticles(limit:Int) {
        getArticlesUseCase(limit).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SpaceNewsState(articles = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = SpaceNewsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SpaceNewsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}


data class SpaceNewsState(
    var isLoading:Boolean=false,
    val articles: List<ArticleDomain> = emptyList(),
    val articlesDetails: ArticleDetailDomain? = null,
    var error:String=""
)


