package com.dawinder.musicplayer_jetpackcompose.player

import com.dawinder.musicplayer_jetpackcompose.model.Track

interface PlayerEvents {
    fun onTrackClick(track: Track)
    fun onPlayPauseClick()
    fun onPreviousClick()
    fun onNextClick()
    fun onSeekBarPositionChanged(position: Long)
}