package com.example.githubapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.model.repository.User
import com.example.githubapp.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

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
    val uiState: MutableState<UiState> = mutableStateOf(UiState.Initial)

    /**
     * 検索フォームに入力された文字列を表すMutableState
     */
    val searchQuery: MutableState<String> = mutableStateOf("")

    fun onSearchTapped() {
        val searchQuery: String = searchQuery.value

        viewModelScope.launch {
            uiState.value = UiState.Loading
            runCatching {
                userRepository.getUser(userName = searchQuery)
            }.onSuccess {
                uiState.value = UiState.Success(user = it)
            }.onFailure {
                uiState.value = UiState.Failure
            }
        }
    }
}