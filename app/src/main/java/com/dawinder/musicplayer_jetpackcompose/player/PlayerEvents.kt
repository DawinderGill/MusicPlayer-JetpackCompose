package com.dawinder.musicplayer_jetpackcompose.player

interface PlayerEvents {
    fun onPlayPauseClick()
    fun onPreviousClick()
    fun onNextClick()
    fun onSeekBarPositionChanged(position: Long)
}