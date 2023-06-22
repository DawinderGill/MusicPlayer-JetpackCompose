package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.example.compose.md_theme_light_primaryContainer

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
        if (track.state == PlayerStates.STATE_PLAYING) {
            Text(text = "-P-")
        }
    }
}