package com.example.instagramhome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PostItem(post: Post) {
    var isLiked by remember { mutableStateOf(false) }
    var isSaved by remember { mutableStateOf(false) }
    var showFullCaption by remember { mutableStateOf(false) }
    var likeCount by remember { mutableStateOf(post.likesCount) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        PostHeader(post = post)

        AsyncImage(
            model = post.imageUrl,
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )

        PostActions(
            isLiked = isLiked,
            isSaved = isSaved,
            onLikeClick = {
                isLiked = !isLiked
                likeCount += if (isLiked) 1 else -1
            },
            onSaveClick = { isSaved = !isSaved }
        )

        Text(
            text = "${likeCount} likes",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        PostCaption(
            post = post,
            showFullCaption = showFullCaption,
            onToggleCaption = { showFullCaption = !showFullCaption }
        )

        if (post.commentsCount > 0) {
            Text(
                text = "View all ${post.commentsCount} comments",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { /* Handle view comments */ }
            )
        }

        Text(
            text = post.timeAgo,
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PostHeader(post: Post) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = post.userProfilePic,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                text = post.username,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            if (post.location.isNotEmpty()) {
                Text(
                    text = post.location,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        IconButton(onClick = { /* Handle more options */ }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More Options",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun PostActions(
    isLiked: Boolean,
    isSaved: Boolean,
    onLikeClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            IconButton(onClick = onLikeClick) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (isLiked) Color.Red else Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }

            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Outlined.ChatBubbleOutline,
                    contentDescription = "Comment",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }

            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Outlined.Send,
                    contentDescription = "Share",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        IconButton(onClick = onSaveClick) {
            Icon(
                imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                contentDescription = "Save",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun PostCaption(
    post: Post,
    showFullCaption: Boolean,
    onToggleCaption: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append("${post.username} ")
        }
        append(post.caption)
    }

    Text(
        text = annotatedString,
        fontSize = 14.sp,
        maxLines = if (showFullCaption) Int.MAX_VALUE else 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onToggleCaption() }
    )

    if (!showFullCaption && post.caption.length > 100) {
        Text(
            text = "more",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { onToggleCaption() }
        )
    }
}
