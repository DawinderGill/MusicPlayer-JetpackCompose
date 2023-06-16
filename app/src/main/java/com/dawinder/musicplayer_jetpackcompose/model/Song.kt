package com.dawinder.musicplayer_jetpackcompose.model

data class Song(
    val songId: Int = 0,
    val songName: String = "",
    val songUrl: String = "",
    val songImage: Int = 0,
    val singerName: String = "",
    var isPlaying: Boolean = false
) {
    class Builder {
        private var songId: Int = 0
        private lateinit var songName: String
        private lateinit var songUrl: String
        private var songImage: Int = 0
        private lateinit var singerName: String
        private var isPlaying: Boolean = false

        fun songId(songId: Int) = apply { this.songId = songId }
        fun songName(songName: String) = apply { this.songName = songName }
        fun songUrl(songUrl: String) = apply { this.songUrl = songUrl }
        fun songImage(songImage: Int) = apply { this.songImage = songImage }
        fun singerName(singerName: String) = apply { this.singerName = singerName }
        fun isPlaying(isPlaying: Boolean) = apply { this.isPlaying = isPlaying }

        fun build(): Song {
            return Song(
                songId,
                songName,
                songUrl,
                songImage,
                singerName,
                isPlaying
            )
        }
    }
}
