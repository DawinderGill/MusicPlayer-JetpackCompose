@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalGlideComposeApi::class)

package com.dawinder.musicplayer_jetpackcompose.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dawinder.musicplayer_jetpackcompose.R
import com.dawinder.musicplayer_jetpackcompose.models.Track
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_onPrimary
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_onPrimaryContainer
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography

@Composable
fun TrackImage(
    trackImage: Int,
    modifier: Modifier
) {
    GlideImage(
        model = trackImage,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.track_image),
        modifier = modifier.clip(shape = RoundedCornerShape(8.dp))
    )
}

@Composable
fun TrackName(trackName: String, modifier: Modifier) {
    Text(
        text = trackName,
        style = typography.bodyLarge,
        color = md_theme_light_onPrimary,
        modifier = modifier.padding(start = 16.dp, end = 8.dp)
    )
}

@Composable
fun PreviousIcon(onClick: () -> Unit, isBottomTab: Boolean) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.icon_previous),
            contentDescription = stringResource(id = R.string.icon_skip_previous),
            tint = if (isBottomTab) md_theme_light_onPrimary else md_theme_light_onPrimaryContainer,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun PlayPauseIcon(selectedTrack: Track, onClick: () -> Unit, isBottomTab: Boolean) {
    if (selectedTrack.state == PlayerStates.STATE_BUFFERING) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size = 48.dp)
                .padding(all = 9.dp),
            color = if (isBottomTab) md_theme_light_onPrimary else md_theme_light_onPrimaryContainer,
        )
    } else {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(
                    id = if (selectedTrack.state == PlayerStates.STATE_PLAYING) R.drawable.icon_pause
                    else R.drawable.icon_play
                ),
                contentDescription = stringResource(id = R.string.icon_play_pause),
                tint = if (isBottomTab) md_theme_light_onPrimary else md_theme_light_onPrimaryContainer,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
fun NextIcon(onClick: () -> Unit, isBottomTab: Boolean) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.icon_next),
            contentDescription = stringResource(id = R.string.icon_skip_next),
            tint = if (isBottomTab) md_theme_light_onPrimary else md_theme_light_onPrimaryContainer,
            modifier = Modifier.size(48.dp)
        )
    }
}
