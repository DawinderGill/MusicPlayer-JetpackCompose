@file:OptIn(ExperimentalMaterialApi::class)

package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.model.Track
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.viewmodel.HomeViewModel
import com.example.compose.md_theme_light_primaryContainer
import kotlinx.coroutines.launch
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.*

//viewModel: HomeViewModel = viewModel()
//viewModel: HomeViewModel = HomeViewModel(SongRepositoryImpl())
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var bottomTabExpanded by remember { mutableStateOf(false) }
    val fullScreenState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()
    val onBottomTabClick: () -> Unit = { scope.launch { fullScreenState.show() } }

    val playerEvents = object : PlayerEvents {
        override fun onTrackClick(track: Track) {
            viewModel.onTrackSelected(track, false)
            bottomTabExpanded = true
        }

        override fun onPlayPauseClick() {
            viewModel.onPlayPauseClick()
        }

        override fun onPreviousClick() {
            viewModel.onPreviousClick(false)
        }

        override fun onNextClick() {
            viewModel.onNextClick(false)
        }

        override fun onSeekBarPositionChanged(position: Long) {
            viewModel.onSeekBarPositionChanged(position)
        }
    }

    TrackList(
        tracks = viewModel.tracks,
        bottomTabExpanded = bottomTabExpanded,
        fullScreenState = fullScreenState,
        selectedTrack = viewModel.selectedTrack,
        onBottomTabClick = onBottomTabClick,
        playerEvents = playerEvents
    )
}

@Composable
fun TrackList(
    tracks: List<Track>,
    bottomTabExpanded: Boolean,
    fullScreenState: ModalBottomSheetState,
    selectedTrack: Track?,
    onBottomTabClick: () -> Unit,
    playerEvents: PlayerEvents
) {
    // Wrap the layout with a ModalBottomSheetLayout to allow full screen expansion
    ModalBottomSheetLayout(
        sheetContent = {
            if (selectedTrack != null) {
                FullScreenContent(
                    selectedTrack = selectedTrack,
                    playerEvents = playerEvents
                )
            }
        }, sheetState = fullScreenState
    ) {
        // Main content
        Column {
            // Display the list of tracks
            LazyColumn(
                modifier = Modifier.weight(1f), contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(tracks) {
                    TrackListItem(track = it, onTrackClick = { playerEvents.onTrackClick(it) })
                }
            }
            // Display the bottom screen content when a track is selected and bottom tab is expanded
            if (selectedTrack != null && bottomTabExpanded) {
                BottomScreenContent(
                    selectedTrack = selectedTrack,
                    onBottomTabClick = onBottomTabClick,
                    playerEvents = playerEvents
                )
            }
        }
    }
}

@Composable
fun TrackListItem(track: Track, onTrackClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onTrackClick)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(md_theme_light_primaryContainer)
        ) /*{
            Image(
                painter = painterResource(song.songImage),
                contentDescription = null, // Provide a proper content description
                modifier = Modifier.fillMaxSize()
            )
        }*/
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = track.trackName, style = typography.bodyLarge, fontWeight = FontWeight.Bold
            )
            Text(
                text = track.artistName, style = typography.bodyLarge, fontStyle = FontStyle.Italic
            )
        }
        if (track.isSelected) {
            Text(text = "-S-")
        }
        if (track.state == STATE_PLAYING) {
            Text(text = "-P-")
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val s = SongRepositoryImpl()
    MusicPlayerJetpackComposeTheme {
        val song = Song.Builder().songName("Song 1").songUrl("").songImage(R.mipmap.ic_launcher)
            .singerName("Artist 1").build()
        SongListItem(song = song) {

        }
    }
}*/
