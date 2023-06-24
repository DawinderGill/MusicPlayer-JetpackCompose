package com.dawinder.musicplayer_jetpackcompose.player

import com.dawinder.musicplayer_jetpackcompose.models.Track

interface PlayerEvents {
    fun onPlayPauseClick()
    fun onPreviousClick()
    fun onNextClick()
    fun onTrackClick(track: Track)
    fun onSeekBarPositionChanged(position: Long)
}