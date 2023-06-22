package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_BUFFERING
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_PLAYING
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.example.compose.md_theme_light_primaryContainer

@Composable
fun BottomPlayerTab(
    selectedTrack: Track, playerEvents: PlayerEvents, onBottomTabClick: () -> Unit
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
            TrackImage()
            TrackName(trackName = selectedTrack.trackName, modifier = Modifier.weight(1f))
            PreviousIcon(onClick = playerEvents::onPreviousClick)
            PlayPauseIcon(selectedTrack = selectedTrack, onClick = playerEvents::onPlayPauseClick)
            NextIcon(onClick = playerEvents::onNextClick)
        }
    }
}

@Composable
fun TrackImage() {
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
}

@Composable
fun TrackName(trackName: String, modifier: Modifier) {
    Text(
        text = trackName,
        style = typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.padding(start = 16.dp, end = 8.dp)
    )
}

@Composable
fun PreviousIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
    }
}

@Composable
fun PlayPauseIcon(selectedTrack: Track, onClick: () -> Unit) {
    if (selectedTrack.state == STATE_BUFFERING) {
        CircularProgressIndicator()
    } else {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = if (selectedTrack.state == STATE_PLAYING) Icons.Default.ThumbUp else Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(45.dp)
            )
        }
    }
}

@Composable
fun NextIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(45.dp)
        )
    }
}