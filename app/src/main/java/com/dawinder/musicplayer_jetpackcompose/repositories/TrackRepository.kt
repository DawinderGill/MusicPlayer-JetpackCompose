package com.dawinder.musicplayer_jetpackcompose.repositories

import com.dawinder.musicplayer_jetpackcompose.models.Track

interface TrackRepository {
    fun getTrackList(): List<Track>
}