package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.model.Track
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.viewmodel.HomeViewModel
import com.example.compose.md_theme_light_primaryContainer
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.*

@Composable
fun BottomScreenContent(
    selectedTrack: Track, onBottomTabClick: () -> Unit, playerEvents: PlayerEvents
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onBottomTabClick)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(md_theme_light_primaryContainer)
            )/*Image(
                painter = painterResource(id = selectedSong.songImage),
                contentDescription = null, // Provide a proper content description
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )*/
            Text(
                text = selectedTrack.trackName,
                style = typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .weight(1f)
            )
            // Previous Icon
            IconButton(onClick = playerEvents::onPreviousClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null, // Provide a proper content description
                    modifier = Modifier.size(45.dp)
                )
            }
            // Play/Pause Icon
            if (selectedTrack.state == STATE_BUFFERING) {
                CircularProgressIndicator()
            } else {
                IconButton(onClick = playerEvents::onPlayPauseClick) {
                    Icon(
                        imageVector = if (selectedTrack.state == STATE_PLAYING) Icons.Default.ThumbUp else Icons.Default.PlayArrow,
                        contentDescription = null, // Provide a proper content description
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
            // Next Icon
            IconButton(onClick = playerEvents::onNextClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null, // Provide a proper content description
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    }
}

@Composable
fun FullScreenContent(
    selectedTrack: Track?, playerEvents: PlayerEvents
) {
    val viewModel: HomeViewModel = viewModel()
    val playbackState =
        viewModel.playbackState.collectAsState(initial = PlaybackState(0L, 0L)).value
    Column(modifier = Modifier.fillMaxWidth()) {
        // Big image at the top
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

        // Track name
        Text(
            text = selectedTrack?.trackName!!,
            style = typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Artist name
        Text(
            text = selectedTrack.artistName,
            style = typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        var currentMediaProgress = playbackState.currentPlaybackPosition.toFloat()
        var currentPosTemp by remember { mutableStateOf(0f) }

        Slider(
            value = if (currentPosTemp == 0f) currentMediaProgress else currentPosTemp,
            onValueChange = { currentPosTemp = it },
            onValueChangeFinished = {
                currentMediaProgress = currentPosTemp
                currentPosTemp = 0f
                playerEvents.onSeekBarPositionChanged(currentMediaProgress.toLong())
            },
            valueRange = 0f..playbackState.currentTrackDuration.toFloat(),
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
            Text(text = formatTime(timeInMillis = playbackState.currentPlaybackPosition))
            Text(text = formatTime(timeInMillis = playbackState.currentTrackDuration))
        }

        // Row with play/pause, previous, and next buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Previous button
            IconButton(onClick = playerEvents::onPreviousClick) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous",
                    modifier = Modifier.size(45.dp)
                )
            }

            if (selectedTrack.state == STATE_BUFFERING) {
                CircularProgressIndicator()
            } else {
                IconButton(onClick = playerEvents::onPlayPauseClick) {
                    Icon(
                        imageVector = if (selectedTrack.state == STATE_PLAYING) Icons.Default.ThumbUp else Icons.Default.PlayArrow,
                        contentDescription = "Play/Pause",
                        modifier = Modifier.size(45.dp)
                    )
                }
            }

            // Next button
            IconButton(onClick = playerEvents::onNextClick) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next",
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    }
}

@Composable
fun formatTime(timeInMillis: Long): String {
    val totalSeconds = timeInMillis / 1000
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}

@Preview
@Composable
fun TestPreview() {/*val s = Song.Builder().songName("Song 1 Test Song Test TextView").songUrl("")
        .songImage(R.mipmap.ic_launcher)
        .singerName("Artist 1").build()*/
    //BottomSheetContent(s)
}
