package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.dawinder.musicplayer_jetpackcompose.utils.formatTime
import com.example.compose.md_theme_light_primaryContainer
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BottomSheetDialog(
    selectedTrack: Track,
    playerEvents: PlayerEvents,
    playbackState: StateFlow<PlaybackState>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TrackInfo(trackName = selectedTrack.trackName, artistName = selectedTrack.artistName)
        TrackProgressSlider(playbackState = playbackState) {
            playerEvents.onSeekBarPositionChanged(it)
        }
        TrackControls(
            selectedTrack = selectedTrack,
            onPreviousClick = playerEvents::onPreviousClick,
            onPlayPauseClick = playerEvents::onPlayPauseClick,
            onNextClick = playerEvents::onNextClick
        )
    }
}

@Composable
fun TrackInfo(trackName: String, artistName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(md_theme_light_primaryContainer)
    )/*Image(
            painter = painterResource(song.songImage),
            contentDescription = null, // Provide a proper content description
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Black) // Placeholder background color
        )*/
    Text(
        text = trackName,
        style = typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    Text(
        text = artistName,
        style = typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun TrackProgressSlider(
    playbackState: StateFlow<PlaybackState>,
    onSeekBarPositionChanged: (Long) -> Unit
) {
    val playbackStateValue = playbackState.collectAsState(
        initial = PlaybackState(0L, 0L)
    ).value
    var currentMediaProgress = playbackStateValue.currentPlaybackPosition.toFloat()
    var currentPosTemp by rememberSaveable { mutableStateOf(0f) }

    Slider(
        value = if (currentPosTemp == 0f) currentMediaProgress else currentPosTemp,
        onValueChange = { currentPosTemp = it },
        onValueChangeFinished = {
            currentMediaProgress = currentPosTemp
            currentPosTemp = 0f
            onSeekBarPositionChanged(currentMediaProgress.toLong())
        },
        valueRange = 0f..playbackStateValue.currentTrackDuration.toFloat(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = playbackStateValue.currentPlaybackPosition.formatTime())
        Text(text = playbackStateValue.currentTrackDuration.formatTime())
    }
}

@Composable
fun TrackControls(
    selectedTrack: Track,
    onPreviousClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PreviousIcon(onClick = onPreviousClick)

        PlayPauseIcon(selectedTrack = selectedTrack, onClick = onPlayPauseClick)

        NextIcon(onClick = onNextClick)
    }
}