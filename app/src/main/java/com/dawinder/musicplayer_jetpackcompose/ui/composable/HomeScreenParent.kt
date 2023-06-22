@file:OptIn(ExperimentalMaterialApi::class)

package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.*
import com.dawinder.musicplayer_jetpackcompose.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun HomeScreenParent(viewModel: HomeViewModel) {
    var bottomTabExpanded by rememberSaveable { mutableStateOf(false) }
    val fullScreenState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()
    val onBottomTabClick: () -> Unit = { scope.launch { fullScreenState.show() } }

    val onTrackClick: (Track) -> Unit = {
        viewModel.onTrackSelected(viewModel.tracks.indexOf(it))
        bottomTabExpanded = true
    }

    TrackList(
        tracks = viewModel.tracks,
        selectedTrack = viewModel.selectedTrack,
        bottomTabExpanded = bottomTabExpanded,
        fullScreenState = fullScreenState,
        playerEvents = viewModel,
        playbackState = viewModel.playbackState,
        onBottomTabClick = onBottomTabClick,
        onTrackClick = onTrackClick
    )
}

@Composable
fun TrackList(
    tracks: List<Track>,
    selectedTrack: Track?,
    bottomTabExpanded: Boolean,
    fullScreenState: ModalBottomSheetState,
    playerEvents: PlayerEvents,
    playbackState: StateFlow<PlaybackState>,
    onBottomTabClick: () -> Unit,
    onTrackClick: (Track) -> Unit
) {
    println("Bottom tab : $bottomTabExpanded")
    ModalBottomSheetLayout(
        sheetContent = {
            if (selectedTrack != null) BottomSheetDialog(selectedTrack, playerEvents, playbackState)
        }, sheetState = fullScreenState
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f), contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(tracks) {
                    TrackListItem(track = it, onTrackClick = { onTrackClick(it) })
                }
            }
            if (selectedTrack != null && bottomTabExpanded) {
                BottomPlayerTab(selectedTrack, playerEvents, onBottomTabClick)
            }
        }
    }
}