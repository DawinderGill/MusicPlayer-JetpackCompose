package com.dawinder.musicplayer_jetpackcompose.model

data class Song(
    val songId: Int?,
    val songName: String,
    val songUrl: String,
    val songImage: Int,
    val singerName: String,
    val isPlaying: Boolean = false
) {
    companion object {
        private var currentId = 0

        fun generateId(): Int {
            currentId++
            return currentId
        }
    }

    class Builder {
        private var songId: Int? = null
        private lateinit var songName: String
        private lateinit var songUrl: String
        private var songImage: Int = 0
        private lateinit var singerName: String
        private var isPlaying: Boolean = false

        fun songId(songId: Int?) = apply { this.songId = songId }
        fun songName(songName: String) = apply { this.songName = songName }
        fun songUrl(songUrl: String) = apply { this.songUrl = songUrl }
        fun songImage(songImage: Int) = apply { this.songImage = songImage }
        fun singerName(singerName: String) = apply { this.singerName = singerName }
        fun isPlaying(isPlaying: Boolean) = apply { this.isPlaying = isPlaying }

        fun build(): Song {
            val id = songId ?: generateId()
            return Song(
                id,
                songName,
                songUrl,
                songImage,
                singerName,
                isPlaying
            )
        }
    }
}
