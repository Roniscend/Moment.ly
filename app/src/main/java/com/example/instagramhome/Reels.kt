package com.example.instagramhome

data class Reel(
    val id: String,
    val username: String,
    val userProfilePic: String,
    val videoUrl: String,
    val caption: String,
    val likesCount: Int,
    val commentsCount: Int,
    val isLiked: Boolean = false,
    val music: String = ""
)

fun getSampleReels(): List<Reel> = listOf(
    Reel(
        id = "r1",
        username = "adventure_seeker",
        userProfilePic = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150&h=150&fit=crop&crop=face",
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        caption = "Epic mountain adventure! 🏔️ #adventure #nature #mountains",
        likesCount = 15420,
        commentsCount = 234,
        music = "Original Audio"
    ),
    Reel(
        id = "r2",
        username = "cooking_master",
        userProfilePic = "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=150&h=150&fit=crop&crop=face",
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        caption = "Perfect pasta recipe in 60 seconds! 🍝 #cooking #foodie #recipe",
        likesCount = 8920,
        commentsCount = 156,
        music = "Cooking Vibes"
    ),
    Reel(
        id = "r3",
        username = "dance_fever",
        userProfilePic = "https://images.unsplash.com/photo-1494790108755-2616b612b786?w=150&h=150&fit=crop&crop=face",
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        caption = "New dance trend! Can you do it? 💃 #dance #trending #viral",
        likesCount = 25680,
        commentsCount = 445,
        music = "Trending Dance Beat"
    ),
    Reel(
        id = "r4",
        username = "art_creator",
        userProfilePic = "https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=150&h=150&fit=crop&crop=face",
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        caption = "Time-lapse painting process 🎨 #art #painting #timelapse",
        likesCount = 12340,
        commentsCount = 89,
        music = "Chill Art Vibes"
    ),
    Reel(
        id = "r5",
        username = "fitness_guru",
        userProfilePic = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=150&h=150&fit=crop&crop=face",
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
        caption = "Quick 5-minute workout! 💪 #fitness #workout #health",
        likesCount = 18760,
        commentsCount = 312,
        music = "Workout Motivation"
    ),
    Reel(
        id = "r6",
        username = "pet_lover",
        userProfilePic = "https://images.unsplash.com/photo-1517841905240-472988babdf9?w=150&h=150&fit=crop&crop=face",
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        caption = "My cat's reaction to new toy! 🐱 #cats #pets #funny",
        likesCount = 34520,
        commentsCount = 678,
        music = "Cute Pet Music"
    ),
    Reel(
        id = "r7",
        username = "travel_diary",
        userProfilePic = "https://images.unsplash.com/photo-1524504388940-b1c1722653e1?w=150&h=150&fit=crop&crop=face",
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4",
        caption = "Beautiful sunset in Bali 🌅 #travel #bali #sunset",
        likesCount = 21890,
        commentsCount = 234,
        music = "Tropical Vibes"
    ),
    Reel(
        id = "r8",
        username = "tech_reviewer",
        userProfilePic = "https://images.unsplash.com/photo-1519244703995-f4e0f30006d5?w=150&h=150&fit=crop&crop=face",
        videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
        caption = "Latest smartphone review! 📱 #tech #smartphone #review",
        likesCount = 9876,
        commentsCount = 145,
        music = "Tech Beat"
    )
)
