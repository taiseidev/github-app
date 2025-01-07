package com.example.githubapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.model.repository.User
import com.example.githubapp.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.hilt.navigation.compose.hiltViewModel

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    sealed class UiState {
        data object Initial : UiState()
        data object Loading : UiState()
        data class Success(val user: User) : UiState()
        data object Failure : UiState()
    }

    /**
     * Viewの状態を[UiState]として表すMutableState
     */
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    /**
     * 検索フォームに入力された文字列を表すMutableState
     */
    val searchQuery: MutableState<String> = mutableStateOf("")

    fun onSearchTapped() {
        val searchQuery: String = searchQuery.value

        viewModelScope.launch {
            _uiState.value = UiState.Loading
            runCatching {
                userRepository.getUser(userName = searchQuery)
            }.onSuccess {
                _uiState.value = UiState.Success(user = it)
            }.onFailure {
                _uiState.value = UiState.Failure
            }
        }
    }
}