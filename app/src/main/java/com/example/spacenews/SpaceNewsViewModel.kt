package com.example.spacenews

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import com.example.spacenews.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import com.example.spacenews.core.Constants
import com.example.spacenews.domain.ArticleDetailDomain
import com.example.spacenews.domain.ArticleDomain
import kotlinx.coroutines.flow.onEach
import com.example.spacenews.domain.GetArticleDetailUseCase
import com.example.spacenews.domain.GetArticlesUseCase

@HiltViewModel
class SpaceNewsViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    savedStateHandle: SavedStateHandle,
    private  val getArticleDetailUseCase: GetArticleDetailUseCase
) : ViewModel() {

    private val coroutineDispatcher = Dispatchers.Main
    private val _state = mutableStateOf(SpaceNewsState())
    val state: State<SpaceNewsState> = _state

    init {
        savedStateHandle.get<Int>(Constants.PARAM_ID)?.let { id ->
            getArticlesDetails(id)
        }
        getArticles(limit = 50)
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


