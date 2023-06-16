package com.dawinder.musicplayer_jetpackcompose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dawinder.musicplayer_jetpackcompose.repository.SongRepositoryImpl
import com.dawinder.musicplayer_jetpackcompose.ui.composable.SongList
import com.dawinder.musicplayer_jetpackcompose.ui.theme.MusicPlayerJetpackComposeTheme
import com.dawinder.musicplayer_jetpackcompose.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicPlayerJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SongList(songs = viewModel.getSongs())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val s = SongRepositoryImpl()
    MusicPlayerJetpackComposeTheme {
        SongList(songs = s.getSongList())
        //Greeting(name = "Hello World")
    }
}