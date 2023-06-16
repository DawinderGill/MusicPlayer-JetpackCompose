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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dawinder.musicplayer_jetpackcompose.model.Song
import com.dawinder.musicplayer_jetpackcompose.repository.SongRepositoryImpl
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.dawinder.musicplayer_jetpackcompose.viewmodel.HomeViewModel
import com.example.compose.md_theme_light_primaryContainer
import kotlinx.coroutines.launch

//viewModel: HomeViewModel = viewModel()
//viewModel: HomeViewModel = HomeViewModel(SongRepositoryImpl())
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var bottomTabExpanded by remember { mutableStateOf(false) }
    val fullScreenState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    SongList(songs = viewModel.songs,
        bottomTabExpanded = bottomTabExpanded,
        fullScreenState = fullScreenState,
        selectedSong = viewModel.selectedSong.value,
        onSongClick = { item ->
            scope.launch {
                viewModel.onSongSelected(item)
                bottomTabExpanded = true
            }
        },
        onBottomTabClick = {
            scope.launch {
                fullScreenState.show()
            }
        },
        onPlayPauseClick = {
            scope.launch {
                viewModel.onPlayPauseClick()
            }
        },
        onPreviousClick = {
            scope.launch {
                viewModel.onPreviousClick()
            }
        },
        onNextClick = {
            scope.launch {
                viewModel.onNextClick()
            }
        })
}

@Composable
fun SongList(
    songs: List<Song>,
    bottomTabExpanded: Boolean,
    fullScreenState: ModalBottomSheetState,
    selectedSong: Song?,
    onSongClick: (Song) -> Unit,
    onBottomTabClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    ModalBottomSheetLayout(
        sheetContent = {
            if (selectedSong != null) {
                FullScreenContent(
                    selectedSong,
                    onPlayPauseClick = onPlayPauseClick,
                    onPreviousClick = onPreviousClick,
                    onNextClick = onNextClick
                )
            }
        }, sheetState = fullScreenState
    ) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f), contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(songs) {
                    SongListItem(song = it, onSongClick = { onSongClick(it) })
                }
            }
            if (selectedSong != null && bottomTabExpanded) {
                BottomScreenContent(
                    selectedSong = selectedSong,
                    onItemClick = onBottomTabClick,
                    onPlayPauseClick = onPlayPauseClick,
                    onPreviousClick = onPreviousClick,
                    onNextClick = onNextClick
                )
            }
        }
    }
}

@Composable
fun SongListItem(song: Song, onSongClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onSongClick)
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
                text = song.songName, style = typography.bodyLarge, fontWeight = FontWeight.Bold
            )
            Text(
                text = song.singerName, style = typography.bodyLarge, fontStyle = FontStyle.Italic
            )
        }
        if (song.isPlaying) {
            Text(text = "Playing")
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
