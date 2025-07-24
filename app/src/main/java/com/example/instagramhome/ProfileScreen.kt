package com.example.instagramhome

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.GridOn
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(authViewModel: AuthViewModel = viewModel()) {
    val profileData = remember { getProfileData() }
    val highlights = remember { getProfileHighlights() }
    val posts = remember { getProfilePosts() }
    val reels = remember { getProfileReels() }
    val tagged = remember { getTaggedPosts() }

    var selectedTab by remember { mutableStateOf(0) }
    var showSettings by remember { mutableStateOf(false) }

    if (showSettings) {
        SettingsScreen(
            onBackClick = { showSettings = false },
            authViewModel = authViewModel
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                ProfileTopBar(
                    onSettingsClick = { showSettings = true },
                    authViewModel = authViewModel
                )
            }

            item {
                ProfileHeader(profileData = profileData)
            }

            item {
                ProfileBio(profileData = profileData)
            }

            item {
                ProfileActions()
            }

            item {
                HighlightsSection(highlights = highlights)
            }

            item {
                ProfileTabs(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            }

            item {
                when (selectedTab) {
                    0 -> PostsGrid(posts = posts)
                    1 -> ReelsGrid(reels = reels)
                    2 -> TaggedGrid(tagged = tagged)
                }
            }
        }
    }
}

@Composable
fun ProfileTopBar(
    onSettingsClick: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    val user = authViewModel.user.value

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = user?.name?.lowercase()?.replace(" ", "_") ?: "username",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        IconButton(onClick = onSettingsClick) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader(profileData: ProfileData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE91E63),
                            Color(0xFFFF9800),
                            Color(0xFFFFEB3B)
                        )
                    ),
                    shape = CircleShape
                )
                .padding(3.dp)
        ) {
            AsyncImage(
                model = profileData.profilePicture,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(20.dp))
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProfileStat(
                count = profileData.postsCount,
                label = "Posts"
            )
            ProfileStat(
                count = profileData.followersCount,
                label = "Followers"
            )
            ProfileStat(
                count = profileData.followingCount,
                label = "Following"
            )
        }
    }
}

@Composable
fun ProfileStat(count: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatProfileCount(count),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun ProfileBio(profileData: ProfileData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = profileData.displayName,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        if (profileData.bio.isNotEmpty()) {
            Text(
                text = profileData.bio,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        if (profileData.website.isNotEmpty()) {
            Text(
                text = profileData.website,
                fontSize = 14.sp,
                color = Color(0xFF0095F6),
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable {  }
            )
        }
    }
}

@Composable
fun ProfileActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = {  },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Edit Profile",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Button(
            onClick = {  },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Share Profile",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        OutlinedButton(
            onClick = {  },
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Add Friend",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun HighlightsSection(highlights: List<Highlight>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            AddHighlightItem()
        }

        items(highlights) { highlight ->
            HighlightItem(highlight = highlight)
        }
    }
}

@Composable
fun AddHighlightItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(70.dp)
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable {  },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Highlight",
                tint = Color.Gray,
                modifier = Modifier.size(32.dp)
            )
        }

        Text(
            text = "New",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            color = Color.Gray
        )
    }
}

@Composable
fun HighlightItem(highlight: Highlight) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(70.dp)
            .clickable {  }
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = CircleShape
                )
                .padding(3.dp)
        ) {
            AsyncImage(
                model = highlight.coverImage,
                contentDescription = highlight.title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = highlight.title,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            color = Color.Black
        )
    }
}

@Composable
fun ProfileTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ProfileTab(
            icon = if (selectedTab == 0) Icons.Filled.GridOn else Icons.Outlined.GridOn,
            isSelected = selectedTab == 0,
            onClick = { onTabSelected(0) }
        )
        ProfileTab(
            icon = if (selectedTab == 1) Icons.Filled.Movie else Icons.Outlined.Movie,
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) }
        )
        ProfileTab(
            icon = if (selectedTab == 2) Icons.Filled.Person else Icons.Outlined.Person,
            isSelected = selectedTab == 2,
            onClick = { onTabSelected(2) }
        )
    }
}

@Composable
fun RowScope.ProfileTab(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .weight(1f)
            .height(48.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Color.Black else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun PostsGrid(posts: List<Post>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(600.dp),
        contentPadding = PaddingValues(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(posts) { post ->
            AsyncImage(
                model = post.imageUrl,
                contentDescription = "Post",
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable { /* View post */ },
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ReelsGrid(reels: List<Reel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(600.dp),
        contentPadding = PaddingValues(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(reels) { reel ->
            Box(
                modifier = Modifier
                    .aspectRatio(0.75f)
                    .clickable { /* View reel */ }
            ) {
                AsyncImage(
                    model = reel.userProfilePic,
                    contentDescription = "Reel",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Icon(
                    imageVector = Icons.Default.Movie,
                    contentDescription = "Reel Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@Composable
fun TaggedGrid(tagged: List<Post>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(600.dp),
        contentPadding = PaddingValues(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(tagged) { post ->
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {  }
            ) {
                AsyncImage(
                    model = post.imageUrl,
                    contentDescription = "Tagged Post",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Tagged Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(4.dp)
                        .size(16.dp)
                )
            }
        }
    }
}
