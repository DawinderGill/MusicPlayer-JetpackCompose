@file:Suppress("EmptyMethod")

package com.dawinder.musicplayer_jetpackcompose.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.MyPlayer
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_END
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_NEXT_TRACK
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_PLAYING
import com.dawinder.musicplayer_jetpackcompose.repositories.TrackRepository
import com.dawinder.musicplayer_jetpackcompose.utils.collectPlayerState
import com.dawinder.musicplayer_jetpackcompose.utils.launchPlaybackStateJob
import com.dawinder.musicplayer_jetpackcompose.utils.resetTracks
import com.dawinder.musicplayer_jetpackcompose.utils.toMediaItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Home screen of the application.
 *
 * This ViewModel is responsible for managing the data for the Home screen. It interacts with the
 * [TrackRepository] to fetch a list of tracks and manages the state of the current track being played.
 *
 * @property trackRepository An injected instance of [TrackRepository] for accessing track data.
 * @property myPlayer An injected instance of [MyPlayer] for controlling media playback.
 */
@Suppress("EmptyMethod")
@HiltViewModel
class HomeViewModel @Inject constructor(
    trackRepository: TrackRepository, private val myPlayer: MyPlayer
) : ViewModel(), PlayerEvents {
    /**
     * A mutable state list of all tracks.
     */
    private val _tracks = mutableStateListOf<Track>()
    /**
     * An immutable snapshot of the current list of tracks.
     */
    val tracks: List<Track> get() = _tracks

    /**
     * A private Boolean variable to keep track of whether a track is currently being played or not.
     */
    private var isTrackPlay: Boolean = false

    /**
     * A public property backed by mutable state that holds the currently selected [Track].
     * It can only be set within the [HomeViewModel] class.
     */
    var selectedTrack: Track? by mutableStateOf(null)
        private set
    /**
     * A private property backed by mutable state that holds the index of the currently selected track.
     */
    private var selectedTrackIndex: Int by mutableStateOf(-1)

    /**
     * A nullable [Job] instance that represents the ongoing process of updating the playback state.
     */
    private var playbackStateJob: Job? = null

    /**
     * A private [MutableStateFlow] that holds the current [PlaybackState].
     * It is used to emit updates about the playback state to observers.
     */
    private val _playbackState = MutableStateFlow(PlaybackState(0L, 0L))
    /**
     * A public property that exposes the [_playbackState] as an immutable [StateFlow] for observers.
     */
    val playbackState: StateFlow<PlaybackState> get() = _playbackState

    /**
     * A private Boolean variable to keep track of whether the track selection is automatic (i.e., due to the completion of a track) or manual.
     */
    private var isAuto: Boolean = false

    /**
     * Initializes the ViewModel. It populates the list of tracks, sets up the media player,
     * and observes the player state.
     */
    init {
        _tracks.addAll(trackRepository.getTrackList())
        myPlayer.iniPlayer(tracks.toMediaItemList())
        observePlayerState()
    }

    /**
     * Handles track selection.
     *
     * @param index The index of the selected track in the track list.
     */
    private fun onTrackSelected(index: Int) {
        if (selectedTrackIndex == -1) isTrackPlay = true
        if (selectedTrackIndex == -1 || selectedTrackIndex != index) {
            _tracks.resetTracks()
            selectedTrackIndex = index
            setUpTrack()
        }
    }

    private fun setUpTrack() {
        if (!isAuto) myPlayer.setUpTrack(selectedTrackIndex, isTrackPlay)
        isAuto = false
    }

    /**
     * Updates the playback state and launches or cancels the playback state job accordingly.
     *
     * @param state The new player state.
     */
    private fun updateState(state: PlayerStates) {
        if (selectedTrackIndex != -1) {
            isTrackPlay = state == STATE_PLAYING
            _tracks[selectedTrackIndex].state = state
            _tracks[selectedTrackIndex].isSelected = true
            selectedTrack = null
            selectedTrack = tracks[selectedTrackIndex]

            updatePlaybackState(state)
            if (state == STATE_NEXT_TRACK) {
                isAuto = true
                onNextClick()
            }
            if (state == STATE_END) onTrackSelected(0)
        }
    }

    private fun observePlayerState() {
        viewModelScope.collectPlayerState(myPlayer, ::updateState)
    }

    private fun updatePlaybackState(state: PlayerStates) {
        playbackStateJob?.cancel()
        playbackStateJob = viewModelScope.launchPlaybackStateJob(_playbackState, state, myPlayer)
    }

    /**
     * Implementation of [PlayerEvents.onPreviousClick].
     * Changes to the previous track if one exists.
     */
    override fun onPreviousClick() {
        if (selectedTrackIndex > 0) onTrackSelected(selectedTrackIndex - 1)
    }

    /**
     * Implementation of [PlayerEvents.onNextClick].
     * Changes to the next track in the list if one exists.
     */
    override fun onNextClick() {
        if (selectedTrackIndex < tracks.size - 1) onTrackSelected(selectedTrackIndex + 1)
    }

    /**
     * Implementation of [PlayerEvents.onPlayPauseClick].
     * Toggles play/pause state of the current track.
     */
    override fun onPlayPauseClick() {
        myPlayer.playPause()
    }

    /**
     * Implementation of [PlayerEvents.onTrackClick].
     * Selects the clicked track from the track list.
     *
     * @param track The track that was clicked.
     */
    override fun onTrackClick(track: Track) {
        onTrackSelected(tracks.indexOf(track))
    }

    /**
     * Implementation of [PlayerEvents.onSeekBarPositionChanged].
     * Seeks to the specified position in the current track.
     *
     * @param position The position to seek to.
     */
    override fun onSeekBarPositionChanged(position: Long) {
        viewModelScope.launch { myPlayer.seekToPosition(position) }
    }

    /**
     * Cleans up the media player when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        myPlayer.releasePlayer()
    }
}
