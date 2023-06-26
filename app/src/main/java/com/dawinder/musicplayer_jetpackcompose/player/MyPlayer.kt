package com.dawinder.musicplayer_jetpackcompose.player

import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_BUFFERING
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_END
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_ERROR
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_IDLE
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_NEXT_TRACK
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_PAUSE
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_PLAYING
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_READY
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MyPlayer @Inject constructor(private val player: ExoPlayer) : Player.Listener {

    val playerState = MutableStateFlow(STATE_IDLE)

    val currentPlaybackPosition: Long
        get() = if (player.currentPosition > 0) player.currentPosition else 0L

    val currentTrackDuration: Long
        get() = if (player.duration > 0) player.duration else 0L

    fun iniPlayer(trackList: MutableList<MediaItem>) {
        player.addListener(this)
        player.setMediaItems(trackList)
        player.prepare()
    }

    fun setUpTrack(index: Int, isTrackPlay: Boolean) {
        if (player.playbackState == Player.STATE_IDLE) player.prepare()
        player.seekTo(index, 0)
        if (isTrackPlay) player.playWhenReady = true
    }

    fun playPause() {
        if (player.playbackState == Player.STATE_IDLE) player.prepare()
        player.playWhenReady = !player.playWhenReady
    }

    fun releasePlayer() {
        player.release()
    }

    fun seekToPosition(position: Long) {
        player.seekTo(position)
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        playerState.tryEmit(STATE_ERROR)
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        if (player.playbackState == Player.STATE_READY) {
            if (playWhenReady) {
                playerState.tryEmit(STATE_PLAYING)
            } else {
                playerState.tryEmit(STATE_PAUSE)
            }
        }
    }

    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        super.onMediaItemTransition(mediaItem, reason)
        if (reason == Player.MEDIA_ITEM_TRANSITION_REASON_AUTO) {
            playerState.tryEmit(STATE_NEXT_TRACK)
            playerState.tryEmit(STATE_PLAYING)
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            Player.STATE_IDLE -> {
                playerState.tryEmit(STATE_IDLE)
            }

            Player.STATE_BUFFERING -> {
                playerState.tryEmit(STATE_BUFFERING)
            }

            Player.STATE_READY -> {
                playerState.tryEmit(STATE_READY)
                if (player.playWhenReady) {
                    playerState.tryEmit(STATE_PLAYING)
                } else {
                    playerState.tryEmit(STATE_PAUSE)
                }
            }

            Player.STATE_ENDED -> {
                playerState.tryEmit(STATE_END)
            }
        }
    }
}