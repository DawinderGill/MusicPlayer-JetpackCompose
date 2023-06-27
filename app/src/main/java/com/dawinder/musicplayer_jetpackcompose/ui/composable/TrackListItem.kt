package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dawinder.musicplayer_jetpackcompose.R
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_PLAYING
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_onPrimary
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_onSurfaceVariant
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_primary
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_surfaceVariant
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography

/**
 * A composable function that displays a list item for a track.
 * The list item includes the track's image, name, and artist.
 * Also includes a click action for the track.
 *
 * @param track The track to be displayed.
 * @param onTrackClick The action to be performed when the track item is clicked.
 */
@Composable
fun TrackListItem(track: Track, onTrackClick: () -> Unit) {
    val bgColor = if (track.isSelected) md_theme_light_primary else md_theme_light_surfaceVariant
    val textColor =
        if (track.isSelected) md_theme_light_onPrimary else md_theme_light_onSurfaceVariant
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(all = 5.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = bgColor)
            .clickable(onClick = onTrackClick)
    ) {
        TrackImage(trackImage = track.trackImage, modifier = Modifier.size(size = 64.dp))
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .weight(weight = 1f)
        ) {

            Text(text = track.trackName, style = typography.bodyLarge, color = textColor)
            Text(text = track.artistName, style = typography.bodySmall, color = textColor)
        }
        if (track.state == STATE_PLAYING) LottieAudioWave()
    }
}

/**
 * A composable function that displays a Lottie animation of an audio wave.
 * The animation loops indefinitely.
 */
@Composable
fun LottieAudioWave() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.audio_wave))
    LottieAnimation(
        composition = composition,
        iterations = Int.MAX_VALUE,
        modifier = Modifier.size(size = 64.dp)
    )
}