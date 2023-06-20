package com.dawinder.musicplayer_jetpackcompose.repositories

import com.dawinder.musicplayer_jetpackcompose.model.Track

interface TrackRepository {
    fun getTrackList(): List<Track>
}