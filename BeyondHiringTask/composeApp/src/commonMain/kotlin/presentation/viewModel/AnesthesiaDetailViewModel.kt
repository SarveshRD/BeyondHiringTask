package presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.example.beyondhiringtask.data.repository.ApiStatus
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.beyondhiringtask.data.repository.AnesthesiaDetailRepository


class AnesthesiaDetailViewModel (private val cookRepository: AnesthesiaDetailRepository) : ViewModel() {


    private val _anesthesiaDetailState = MutableStateFlow(AnesthesiaDetailState())
    private val _anesthesiaDetailViewState = MutableStateFlow<AnesthesiaDetailScreenState>(
        AnesthesiaDetailScreenState.Loading
    )
    val anesthesiaDetailViewState = _anesthesiaDetailViewState.asStateFlow()

    suspend fun getData() {
        viewModelScope.launch {
            try {
                cookRepository.getData()
                    .collect { response ->
                        when (response.status) {
                            ApiStatus.LOADING -> {
                                _anesthesiaDetailState.update { it.copy(isLoading = true) }
                            }

                            ApiStatus.SUCCESS -> {
                                _anesthesiaDetailState.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = "",
                                        response.data
                                    )
                                }
                            }

                            ApiStatus.ERROR -> {
                                _anesthesiaDetailState.update {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = response.message
                                    )
                                }
                            }
                        }
                        _anesthesiaDetailViewState.value = _anesthesiaDetailState.value.toUiState()
                    }
            } catch (e: Exception) {
                _anesthesiaDetailState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to fetch data"
                    )
                }
            }
        }
    }

    sealed class AnesthesiaDetailScreenState {
        object Loading : AnesthesiaDetailScreenState()
        class Error(val errorMessage: String) : AnesthesiaDetailScreenState()
        class Success(val responseData: Any) : AnesthesiaDetailScreenState()
    }

    private data class AnesthesiaDetailState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val responseData: Any? = null
    ) {
        fun toUiState(): AnesthesiaDetailScreenState {
            return if (isLoading) {
                AnesthesiaDetailScreenState.Loading
            } else if (errorMessage?.isNotEmpty() == true) {
                AnesthesiaDetailScreenState.Error(errorMessage)
            } else {
                AnesthesiaDetailScreenState.Success(responseData!!)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}