package com.dawinder.musicplayer_jetpackcompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dawinder.musicplayer_jetpackcompose.model.Song
import com.dawinder.musicplayer_jetpackcompose.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(songRepository: SongRepository) : ViewModel() {

    private var _songs = mutableStateListOf<Song>()
    val songs: List<Song> get() = _songs

    var selectedSong: MutableState<Song?> = mutableStateOf(null)
    var isMusicPlaying: MutableState<Boolean> = mutableStateOf(false)

    init {
        _songs.addAll(songRepository.getSongList())
    }

    fun onSongSelected(item: Song) {
        if (selectedSong.value == null || selectedSong.value != item) {
            isMusicPlaying.value = true
            _songs.forEach { it.isPlaying = false }
            _songs.find { it == item }?.let {
                it.isPlaying = true
                selectedSong.value = it
            }
        }
    }

    fun onPreviousClick() {
        val selectedSongIndex: Int = songs.indexOf(selectedSong.value)
        if (selectedSongIndex > 0) onSongSelected(songs[selectedSongIndex - 1])
    }

    fun onNextClick() {
        val selectedSongIndex: Int = songs.indexOf(selectedSong.value)
        if (selectedSongIndex < songs.size - 1) onSongSelected(songs[selectedSongIndex + 1])
    }

    fun onPlayPauseClick() {
        isMusicPlaying.value = !isMusicPlaying.value
    }
}
