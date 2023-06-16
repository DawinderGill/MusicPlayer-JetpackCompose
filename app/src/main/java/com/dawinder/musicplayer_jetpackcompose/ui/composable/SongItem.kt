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
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dawinder.musicplayer_jetpackcompose.R
import com.dawinder.musicplayer_jetpackcompose.model.Song
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.example.compose.md_theme_light_primaryContainer
import kotlinx.coroutines.launch

@Composable
fun SongList(songs: List<Song>) {
    val fullSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
        )
    val bottomSheetExpanded = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val song = Song.Builder().songName("Song 1").songUrl("").songImage(R.mipmap.ic_launcher)
        .singerName("Artist 1").build()

    ModalBottomSheetLayout(
        sheetContent = {
            FullScreenContent(song,
                onPlayPauseClick = {},
                onPreviousClick = {},
                onNextClick = {})
        },
        sheetState = fullSheetState
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(songs) { song ->
                    SongListItem(song = song) {
                        scope.launch {
                            bottomSheetExpanded.value = true
                        }
                    }
                }
            }
            if (bottomSheetExpanded.value) {
                BottomScreenContent(selectedSong = songs.last()) {
                    scope.launch {
                        fullSheetState.show()
                    }
                }
            }
        }
    }
}

@Composable
fun SongListItem(song: Song, onItemClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onItemClick)
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
                text = song.songName,
                style = typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = song.singerName,
                style = typography.bodyLarge,
                fontStyle = FontStyle.Italic
            )
        }
        /*if (song.isPlaying) {
            AnimatedBars()
        }*/
    }
}

@Composable
fun AnimatedBars() {
    // Add your animated bars implementation here
    // Example: You can use a custom composable or an existing animation library
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
