package com.example.githubapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubapp.model.repository.User
import com.example.githubapp.viewmodel.MainViewModel


@Composable
fun MainView(mainViewModel: MainViewModel = hiltViewModel()) {
    val uiState by mainViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SearchView(
            searchQuery = mainViewModel.searchQuery,
            onSearchButtonTapped = mainViewModel::onSearchTapped
        )
        when (uiState) {
            is MainViewModel.UiState.Initial -> {
                InitialView()
            }

            is MainViewModel.UiState.Loading -> {
                LoadingView()
            }

            is MainViewModel.UiState.Success -> {
                UserDetailView(user = uiState.requireUser())
            }

            is MainViewModel.UiState.Failure -> {
                ErrorView()
            }
        }
    }
}

private fun MainViewModel.UiState.requireUser(): User {
    if (this !is MainViewModel.UiState.Success) throw IllegalStateException("user is not loaded")
    return user
}

@Composable
fun SearchView(
    searchQuery: MutableState<String>,
    onSearchButtonTapped: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = {
                searchQuery.value = it
            })
        Button(
            onClick = {
                onSearchButtonTapped()
            }
        ) {
            Text(
                text = "検索"
            )
        }
    }
}

@Composable
fun InitialView() {
    Text("検索してください")
}

@Composable
fun LoadingView() {
    Text("読み込み中")
}

@Composable
fun UserDetailView(user: User) {
    Text(user.id.value.toString())
    Text(user.name)
    Text(user.avatarUrl.url.value)
    Text(user.blogUrl.value)
}

@Composable
fun ErrorView() {
    Text("読み込み失敗")
}