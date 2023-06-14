package com.dawinder.musicplayer_jetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.dawinder.musicplayer_jetpackcompose.model.Song
import com.dawinder.musicplayer_jetpackcompose.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val songRepository: SongRepository) : ViewModel() {

    fun getSongs(): List<Song> {
        return songRepository.getSongList()
    }
}
