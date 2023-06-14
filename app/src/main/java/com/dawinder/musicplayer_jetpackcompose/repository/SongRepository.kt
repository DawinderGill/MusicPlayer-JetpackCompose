package com.dawinder.musicplayer_jetpackcompose.repository

import com.dawinder.musicplayer_jetpackcompose.model.Song

interface SongRepository {
    fun getSongList(): List<Song>
}