package com.example.instagramhome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InstagramHomeScreen(authViewModel: AuthViewModel = viewModel()) {
    val stories = getSampleStories()
    val posts = getSamplePosts()
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            if (selectedTab == 0) InstagramTopBar()
        },
        bottomBar = {
            InstagramBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    item {
                        StoriesSection(stories = stories)
                    }

                    items(posts) { post ->
                        PostItem(post = post)
                        Divider(
                            thickness = 0.5.dp,
                            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        )
                    }
                }
            }
            1 -> {
                DummyScreen("Search")
            }
            2 -> {
                DummyScreen("Add Post")
            }
            3 -> {
                ReelsScreen()
            }
            4 -> {
                ProfileScreen(authViewModel = authViewModel)
            }
        }
    }
}

@Composable
fun DummyScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$title Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
