package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_primary

/**
 * [BottomPlayerTab] is a composable that represents the bottom player tab UI in the application.
 * This tab displays the currently selected track information and provides controls for playback.
 *
 * @param selectedTrack The [Track] object that is currently selected for playback.
 * @param playerEvents The [PlayerEvents] object which encapsulates all the events associated with the player like play, pause, next, previous.
 * @param onBottomTabClick A lambda which gets executed when the bottom player tab is clicked.
 */
@Composable
fun BottomPlayerTab(
    selectedTrack: Track, playerEvents: PlayerEvents, onBottomTabClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(color = md_theme_light_primary)
            .clickable(onClick = onBottomTabClick)
            .padding(all = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            TrackImage(
                trackImage = selectedTrack.trackImage,
                modifier = Modifier.size(size = 70.dp)
            )
            TrackName(trackName = selectedTrack.trackName, modifier = Modifier.weight(1f))
            PreviousIcon(onClick = playerEvents::onPreviousClick, isBottomTab = true)
            PlayPauseIcon(
                selectedTrack = selectedTrack,
                onClick = playerEvents::onPlayPauseClick,
                isBottomTab = true
            )
            NextIcon(onClick = playerEvents::onNextClick, isBottomTab = true)
        }
    }
}