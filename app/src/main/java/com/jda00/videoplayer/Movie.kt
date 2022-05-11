package com.jda00.videoplayer

/**
Model data class
 */


data class Movie(
    var title: String,
    var hlsURL: String,
    var fullURL: String,
    var description: String,
    var publishedAt: String,
    var author: Author

) {
    override fun toString(): String {
        return "\nTitle: $title'\nAuthor: $author\nURL: $fullURL"
    }

    data class Author(
        var name: String,
    )
}

