package com.dawinder.musicplayer_jetpackcompose.models

import com.dawinder.musicplayer_jetpackcompose.BuildConfig
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_IDLE

/**
 * Represents a single music track.
 *
 * @property trackId The unique identifier for the track.
 * @property trackName The name of the track.
 * @property trackUrl The URL of the track.
 * @property trackImage The resource identifier of the track's image.
 * @property artistName The name of the artist of the track.
 * @property isSelected Indicates if the track is currently selected.
 * @property state The current playback state of the track.
 */
data class Track(
    val trackId: Int = 0,
    val trackName: String = "",
    val trackUrl: String = "",
    val trackImage: Int = 0,
    val artistName: String = "",
    var isSelected: Boolean = false,
    var state: PlayerStates = STATE_IDLE
) {
    /**
     * Builder class for [Track].
     *
     * This allows for the incremental construction of a [Track] object.
     */
    class Builder {
        private var trackId: Int = 0
        private lateinit var trackName: String
        private lateinit var trackUrl: String
        private var trackImage: Int = 0
        private lateinit var artistName: String
        private var isSelected: Boolean = false
        private var state: PlayerStates = STATE_IDLE

        fun trackId(trackId: Int) = apply { this.trackId = trackId }
        fun trackName(trackName: String) = apply { this.trackName = trackName }
        fun trackUrl(trackUrl: String) = apply { this.trackUrl = BuildConfig.BASE_URL + trackUrl }
        fun trackImage(trackImage: Int) = apply { this.trackImage = trackImage }
        fun artistName(artistName: String) = apply { this.artistName = artistName }

        /**
         * Builds and returns a [Track] object.
         *
         * @return A [Track] object with the set properties.
         */
        fun build(): Track {
            return Track(
                trackId,
                trackName,
                trackUrl,
                trackImage,
                artistName,
                isSelected,
                state
            )
        }
    }
}
