package com.example.instagramhome

data class ProfileData(
    val username: String,
    val displayName: String,
    val profilePicture: String,
    val bio: String,
    val website: String,
    val postsCount: Int,
    val followersCount: Int,
    val followingCount: Int,
    val isVerified: Boolean = false
)

data class Highlight(
    val id: String,
    val title: String,
    val coverImage: String,
    val storiesCount: Int
)

fun getProfileData(): ProfileData {
    return ProfileData(
        username = "john_doe_dev",
        displayName = "John Doe",
        profilePicture = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=150&h=150&fit=crop&crop=face",
        bio = "🚀 Android Developer | Kotlin Enthusiast\n📱 Building awesome mobile apps\n🌍 Based in San Francisco",
        website = "johndoe.dev",
        postsCount = 247,
        followersCount = 12500,
        followingCount = 892,
        isVerified = false
    )
}

fun getProfileHighlights(): List<Highlight> {
    return listOf(
        Highlight(
            id = "h1",
            title = "Travel",
            coverImage = "https://images.unsplash.com/photo-1469474968028-56623f02e42e?w=150&h=150&fit=crop",
            storiesCount = 8
        ),
        Highlight(
            id = "h2",
            title = "Work",
            coverImage = "https://images.unsplash.com/photo-1486312338219-ce68e2c6b9bd?w=150&h=150&fit=crop",
            storiesCount = 12
        ),
        Highlight(
            id = "h3",
            title = "Food",
            coverImage = "https://images.unsplash.com/photo-1565299624946-b28f40a0ca4b?w=150&h=150&fit=crop",
            storiesCount = 6
        ),
        Highlight(
            id = "h4",
            title = "Fitness",
            coverImage = "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=150&h=150&fit=crop",
            storiesCount = 15
        ),
        Highlight(
            id = "h5",
            title = "Coding",
            coverImage = "https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=150&h=150&fit=crop",
            storiesCount = 20
        )
    )
}

fun getProfilePosts(): List<Post> {
    return getSamplePosts()
}

fun getProfileReels(): List<Reel> {
    return getSampleReels()
}

fun getTaggedPosts(): List<Post> {
    return getSamplePosts().take(6)
}

fun formatProfileCount(count: Int): String {
    return when {
        count >= 1000000 -> "${count / 1000000}M"
        count >= 1000 -> "${count / 1000}K"
        else -> count.toString()
    }
}
