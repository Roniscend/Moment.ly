package com.example.instagramhome

data class Post(
    val id: String,
    val username: String,
    val userProfilePic: String,
    val imageUrl: String,
    val caption: String,
    val location: String = "",
    val likesCount: Int,
    val commentsCount: Int,
    val timeAgo: String
)

data class Story(
    val id: String,
    val username: String,
    val imageUrl: String,
    val isViewed: Boolean = false
)

fun getSamplePosts(): List<Post> = listOf(
    Post(
        id = "1",
        username = "john_doe",
        userProfilePic = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=150&h=150&fit=crop&crop=face",
        imageUrl = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=400&h=400&fit=crop",
        caption = "Beautiful sunset today! 🌅 Perfect end to an amazing day. Nature never ceases to amaze me with its incredible beauty and peaceful vibes. #nature #photography #sunset #peaceful",
        location = "Malibu, California",
        likesCount = 1234,
        commentsCount = 56,
        timeAgo = "2 hours ago"
    ),
    Post(
        id = "2",
        username = "jane_smith",
        userProfilePic = "https://images.unsplash.com/photo-1494790108755-2616b612b786?w=150&h=150&fit=crop&crop=face",
        imageUrl = "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400&h=400&fit=crop",
        caption = "Coffee and coding session ☕️💻 Starting the day right with some fresh brew and clean code. There's nothing better than the combination of caffeine and creativity! #developer #coffee #coding #morning",
        likesCount = 892,
        commentsCount = 23,
        timeAgo = "4 hours ago"
    ),
    Post(
        id = "3",
        username = "travel_enthusiast",
        userProfilePic = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150&h=150&fit=crop&crop=face",
        imageUrl = "https://images.unsplash.com/photo-1464822759844-d150baec013c?w=400&h=400&fit=crop",
        caption = "Exploring the mountains! The view from up here is absolutely breathtaking. Nature never fails to amaze me with its beauty and magnificence. Every step up was worth this incredible view! 🏔️⛰️ #hiking #mountains #adventure #explore",
        location = "Swiss Alps",
        likesCount = 2156,
        commentsCount = 78,
        timeAgo = "6 hours ago"
    ),
    Post(
        id = "4",
        username = "foodie_heaven",
        userProfilePic = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=150&h=150&fit=crop&crop=face",
        imageUrl = "https://images.unsplash.com/photo-1565299624946-b28f40a0ca4b?w=400&h=400&fit=crop",
        caption = "Homemade pizza night! 🍕 Nothing beats the satisfaction of making your own dough from scratch. The perfect blend of crispy crust and melted cheese. #homemade #pizza #cooking #delicious",
        location = "Home Kitchen",
        likesCount = 567,
        commentsCount = 34,
        timeAgo = "8 hours ago"
    ),
    Post(
        id = "5",
        username = "art_lover",
        userProfilePic = "https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=150&h=150&fit=crop&crop=face",
        imageUrl = "https://images.unsplash.com/photo-1541961017774-22349e4a1262?w=400&h=400&fit=crop",
        caption = "Lost in the world of colors and creativity 🎨 Sometimes the best therapy is just letting your imagination flow onto the canvas. Art speaks where words fail. #art #painting #creativity #inspiration",
        location = "Art Studio",
        likesCount = 823,
        commentsCount = 45,
        timeAgo = "10 hours ago"
    )
)

fun getSampleStories(): List<Story> = listOf(
    Story("1", "alice_w", "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=150&h=150&fit=crop&crop=face"),
    Story("2", "bob_jones", "https://images.unsplash.com/photo-1519244703995-f4e0f30006d5?w=150&h=150&fit=crop&crop=face"),
    Story("3", "sarah_k", "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?w=150&h=150&fit=crop&crop=face"),
    Story("4", "mike_dev", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150&h=150&fit=crop&crop=face"),
    Story("5", "emma_photo", "https://images.unsplash.com/photo-1506277886164-e25aa3f4ef7f?w=150&h=150&fit=crop&crop=face"),
    Story("6", "alex_art", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=150&h=150&fit=crop&crop=face")
)

