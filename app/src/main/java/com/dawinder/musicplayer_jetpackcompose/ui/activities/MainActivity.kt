package com.dawinder.musicplayer_jetpackcompose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dawinder.musicplayer_jetpackcompose.ui.composable.HomeScreen
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
                Surface(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    /*val viewModel = HomeViewModel(SongRepositoryImpl(), PlayerManager(LocalContext.current))
    MusicPlayerJetpackComposeTheme { HomeScreen(viewModel) }*/
}