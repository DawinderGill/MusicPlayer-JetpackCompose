package com.dawinder.musicplayer_jetpackcompose.player

data class PlaybackState(
    val currentPlaybackPosition: Long,
    val currentTrackDuration: Long
)