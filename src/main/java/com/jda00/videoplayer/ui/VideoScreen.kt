package com.jda00.videoplayer

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.TracksInfo
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.jda00.videoplayer.data.Movie
import com.jda00.videoplayer.ui.Typography
import com.jda00.videoplayer.util.Constants
import io.noties.markwon.Markwon

/**
UI Composables and application logic
 */


@Composable
fun VideoScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopBar(mainViewModel.movies)

    }
}

// TopBar composable function
@Composable
fun TopBar(
    movies: List<Movie>,
) {
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

    ExoPlayer(movies = movies)

}


// ExoPlayer composable function
@Composable
fun ExoPlayer(
    movies: List<Movie>,
) {
    val context = LocalContext.current

    // sortBy Movie's earliest date
    val sortedMovies = movies.sortedBy { it.publishedAt }

    // Declare videoIndex as remembered mutable state
    var videoIndex by remember { mutableStateOf(0) }


//    Log.i("DATES", sortedMovies[0].publishedAt)


//    Initializing TrackSelector
    val trackSelector = DefaultTrackSelector(context)

    // Initializing ExoPLayer
    val exoPlayer = remember(context) {


        // Attach media to playlist
        com.google.android.exoplayer2.ExoPlayer.Builder(context).build().apply {

            val movie = MediaItem.Builder()
                .setUri(Uri.parse(sortedMovies[0].hlsURL))
                .build()
            setMediaItem(movie)


            val movie2 = MediaItem.Builder()
                .setUri(Uri.parse(sortedMovies[1].hlsURL))
                .build()
            addMediaItem(movie2)

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




            /* Ran out of time with the ExoPlayer docs to experiment more but suspect answer
            lies in doing more with overriding XMLs. I suspect this would be a lot easier without using Compose.
            */

//            if (videoIndex == 0) {
//                setShowPreviousButton(false)
//            } else setShowPreviousButton(true)






        }
    })


    // Updates videoIndex on track change
    exoPlayer.addListener(object : Player.Listener {
        override fun onTracksInfoChanged(tracksInfo: TracksInfo) {

            videoIndex = exoPlayer.currentPeriodIndex


        }
    })


    // Use state hoisting check for changes in index
    TextCard(movies = sortedMovies, videoIndex = videoIndex, onIndexChange = { videoIndex = it })

}


// TextCard composable function
@Composable
fun TextCard(movies: List<Movie>, videoIndex: Int, onIndexChange: (Int) -> Unit) {


    Log.i("CURRENT_INDEX_TEXT ", "$videoIndex")

    val context = LocalContext.current

    // obtain an instance of Markwon
    val markwon: Markwon = Markwon.create(context)

    // parse markdown and create styled text
    var markdown = markwon.toMarkdown(movies[videoIndex].description)


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = movies[videoIndex].title,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp),
            style = Typography.h1,
            textAlign = TextAlign.Left,
        )


        Text(
            text = movies[videoIndex].author.name,
            modifier = Modifier
                .padding(start = 10.dp),
            style = Typography.h2,
            textAlign = TextAlign.Left,
        )


        Text(
            text = markdown.toString(),
            modifier = Modifier
                .padding(start = 10.dp, top = 15.dp, end = 10.dp)
                .verticalScroll(rememberScrollState()),
            style = Typography.body1,
            textAlign = TextAlign.Left,
        )
    }

}


