@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)

package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.R
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_primary
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.dawinder.musicplayer_jetpackcompose.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * A composable function that hosts the home screen of the application.
 * It manages the state of the bottom sheet and when to show it.
 *
 * @param viewModel The ViewModel that is responsible for providing data to the UI and processing user actions.
 */
@Composable
fun HomeScreenParent(viewModel: HomeViewModel) {
    val fullScreenState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()
    val onBottomTabClick: () -> Unit = { scope.launch { fullScreenState.show() } }

    TrackList(
        tracks = viewModel.tracks,
        selectedTrack = viewModel.selectedTrack,
        fullScreenState = fullScreenState,
        playerEvents = viewModel,
        playbackState = viewModel.playbackState,
        onBottomTabClick = onBottomTabClick
    )
}

/**
 * A composable function that displays a list of tracks and a bottom sheet dialog.
 * The bottom sheet dialog is shown when a track is selected.
 *
 * @param tracks A list of tracks to be displayed.
 * @param selectedTrack The currently selected track. If null, no track is selected.
 * @param fullScreenState The state of the bottom sheet dialog.
 * @param playerEvents The events that the player can trigger.
 * @param playbackState The state of the media playback.
 * @param onBottomTabClick A lambda function that is invoked when the bottom tab is clicked.
 */
@Composable
fun TrackList(
    tracks: List<Track>,
    selectedTrack: Track?,
    fullScreenState: ModalBottomSheetState,
    playerEvents: PlayerEvents,
    playbackState: StateFlow<PlaybackState>,
    onBottomTabClick: () -> Unit
) {
    ModalBottomSheetLayout(
        sheetContent = {
            if (selectedTrack != null) BottomSheetDialog(
                selectedTrack, playerEvents, playbackState
            )
        },
        sheetState = fullScreenState,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetElevation = 8.dp
    ) {
        Scaffold(topBar = {
            Surface(shadowElevation = 5.dp) {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_title),
                            style = typography.titleLarge
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = md_theme_light_primary)
                )
            }
        }) { paddingValues ->
            Box(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())) {
                Column {
                    LazyColumn(
                        modifier = Modifier.weight(weight = 1f),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(tracks) {
                            TrackListItem(
                                track = it,
                                onTrackClick = { playerEvents.onTrackClick(it) })
                        }
                    }
                    AnimatedVisibility(
                        visible = selectedTrack != null,
                        enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight })
                    ) {
                        BottomPlayerTab(selectedTrack!!, playerEvents, onBottomTabClick)
                    }
                }
            }
        }
    }
}