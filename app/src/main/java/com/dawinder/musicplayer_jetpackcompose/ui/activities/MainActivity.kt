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
import com.dawinder.musicplayer_jetpackcompose.ui.composable.HomeScreenParent
import com.dawinder.musicplayer_jetpackcompose.ui.theme.MusicPlayerJetpackComposeTheme
import com.dawinder.musicplayer_jetpackcompose.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * [MainActivity] is the main entry point of the app.
 * It is annotated with [@AndroidEntryPoint] to enable field injection via Hilt.
 * This class extends [ComponentActivity], which is a lean version of [AppCompatActivity].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * The [HomeViewModel] instance is obtained via the [viewModels] delegate,
     * which uses the activity as the ViewModelStoreOwner.
     */
    private val viewModel: HomeViewModel by viewModels()

    /**
     * The [onCreate] method is called when the activity is starting.
     * It sets up the UI content and associates it with the activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     * then this Bundle contains the data it most recently supplied in [onSaveInstanceState].
     * Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set the theme of the app to MusicPlayerJetpackComposeTheme.
            MusicPlayerJetpackComposeTheme {
                // Create a surface container using the Surface Composable.
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Setup the HomeScreenParent with the viewModel.
                    HomeScreenParent(viewModel)
                }
            }
        }
    }
}