@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)

package com.dawinder.musicplayer_jetpackcompose.ui.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrackList(
    tracks: List<Track>,
    selectedTrack: Track?,
    fullScreenState: ModalBottomSheetState,
    playerEvents: PlayerEvents,
    playbackState: StateFlow<PlaybackState>,
    onBottomTabClick: () -> Unit
) {
    /*Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_title),
                    style = typography.titleLarge
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = md_theme_light_primary)
        )
    }) {*/
    ModalBottomSheetLayout(
        sheetContent = {
            if (selectedTrack != null) BottomSheetDialog(
                selectedTrack, playerEvents, playbackState
            )
        },
        sheetState = fullScreenState,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(weight = 1f), contentPadding = PaddingValues(5.dp)
            ) {
                items(tracks) {
                    TrackListItem(
                        track = it,
                        onTrackClick = { playerEvents.onTrackClick(it) }
                    )
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
    //}
}