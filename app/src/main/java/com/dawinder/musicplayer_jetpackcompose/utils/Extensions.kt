package com.dawinder.musicplayer_jetpackcompose.utils

import androidx.media3.common.MediaItem
import com.dawinder.musicplayer_jetpackcompose.model.Track
import com.dawinder.musicplayer_jetpackcompose.player.MyPlayer
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

fun MutableList<Track>.resetTracks() {
    this.forEach { track ->
        track.isSelected = false
        track.state = STATE_IDLE
    }
}

fun List<Track>.toMediaItemList(): MutableList<MediaItem> {
    return this.map { MediaItem.fromUri(it.trackUrl) }.toMutableList()
}

fun CoroutineScope.collectPlayerState(
    myPlayer: MyPlayer,
    updateState: (PlayerStates) -> Unit
) {
    this.launch {
        myPlayer.playerState.collect {
            updateState(it)
        }
    }
}

fun CoroutineScope.launchPlaybackStateJob(
    playbackStateFlow: MutableStateFlow<PlaybackState>,
    state: PlayerStates,
    myPlayer: MyPlayer
) = launch {
    do {
        playbackStateFlow.emit(
            PlaybackState(
                currentPlaybackPosition = myPlayer.currentPlaybackPosition,
                currentTrackDuration = myPlayer.currentTrackDuration
            )
        )
        delay(1000) // delay for 1 second
    } while (state == STATE_PLAYING && isActive)
}

