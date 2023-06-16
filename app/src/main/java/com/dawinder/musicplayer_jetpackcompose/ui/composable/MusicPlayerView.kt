@file:OptIn(ExperimentalMaterialApi::class)

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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.R
import com.dawinder.musicplayer_jetpackcompose.model.Song
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.example.compose.md_theme_light_primaryContainer

@Composable
fun BottomScreenContent(
    selectedSong: Song, onItemClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(md_theme_light_primaryContainer)
            )
            /*Image(
                painter = painterResource(id = selectedSong.songImage),
                contentDescription = null, // Provide a proper content description
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )*/
            Text(
                text = selectedSong.songName,
                style = typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .weight(1f)
            )
            // Previous Icon
            IconButton(onClick = onPreviousClick) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null, // Provide a proper content description
                    modifier = Modifier.size(45.dp)
                )
            }
            // Play/Pause Icon
            IconButton(onClick = onPlayPauseClick) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null, // Provide a proper content description
                    modifier = Modifier.size(45.dp)
                )
            }
            // Next Icon
            IconButton(onClick = onNextClick) {
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
    song: Song?,
    onPlayPauseClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Big image at the top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(md_theme_light_primaryContainer)
        )
        /*Image(
            painter = painterResource(song.songImage),
            contentDescription = null, // Provide a proper content description
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Black) // Placeholder background color
        )*/

        // Song name
        Text(
            text = song?.songName!!,
            style = typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Artist name
        Text(
            text = song.singerName,
            style = typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Progress bar
        // Replace this with your custom progress bar implementation
        LinearProgressIndicator(
            progress = 0.5f, // Example value, replace with actual progress
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        // Row with play/pause, previous, and next buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Previous button
            IconButton(onClick = onPreviousClick) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous",
                    modifier = Modifier.size(45.dp)
                )
            }

            // Play/Pause button
            IconButton(onClick = onPlayPauseClick) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Play/Pause",
                    modifier = Modifier.size(45.dp)
                )
            }

            // Next button
            IconButton(onClick = onNextClick) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next",
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun TestPreview() {
    val s = Song.Builder().songName("Song 1 Test Song Test TextView").songUrl("")
        .songImage(R.mipmap.ic_launcher)
        .singerName("Artist 1").build()
    //BottomSheetContent(s)
}
