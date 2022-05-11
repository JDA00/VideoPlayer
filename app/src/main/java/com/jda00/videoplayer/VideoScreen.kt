package com.jda00.videoplayer

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.TracksInfo
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.jda00.videoplayer.ui.Typography

/**
UI Composables
 */


@Composable
fun VideoScreen() {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopBar()

    }
}

// TopBar composable function
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = Constants.APP_NAME,
                color = Color.White,
                style = Typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .composed {
                        Modifier.padding(top = 8.dp)
                    },
            )
        },
        backgroundColor = Color.Black,
        elevation = 4.dp
    )


}


// ExoPlayer composable function
@Composable
fun ExoPlayer() {
    val context = LocalContext.current


//    Initializing TrackSelector
    val trackSelector = DefaultTrackSelector(context)

    // Initializing ExoPLayer
    val exoPlayer = remember(context) {


        // Attach media to playlist
        com.google.android.exoplayer2.ExoPlayer.Builder(context).build().apply {

            val movie = MediaItem.Builder()
                .setUri(Uri.parse(""))
                .build()
            setMediaItem(movie)


            // Disable autoplay
            playWhenReady = false

            // Select lowest quality playback
            trackSelector.parameters.forceLowestBitrate


            prepare()
        }
    }


// Implementing ExoPlayer
    AndroidView(factory = { context ->
        StyledPlayerView(context).apply {
            player = exoPlayer


        }
    })


}




