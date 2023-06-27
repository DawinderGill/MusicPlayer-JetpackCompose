package com.dawinder.musicplayer_jetpackcompose.repositories

import com.dawinder.musicplayer_jetpackcompose.R
import com.dawinder.musicplayer_jetpackcompose.models.Track
import javax.inject.Inject

/**
 * A concrete implementation of the [TrackRepository] interface.
 * This class is responsible for managing and providing tracks.
 *
 * @constructor Creates an instance of [TrackRepositoryImpl].
 */
class TrackRepositoryImpl @Inject constructor() : TrackRepository {

    /**
     * A list of tracks stored in-memory.
     */
    private val tracks = mutableListOf<Track>()

    /**
     * Initializes the tracks repository.
     */
    init {
        // Initialize songs here or load from a data source
        createTracks()
    }

    /**
     * Creates a set of tracks and adds them to the repository.
     */
    private fun createTracks() {
        // Add songs to the repository
        tracks.add(
            Track.Builder().trackName("295").trackUrl("295.mp3")
                .trackImage(R.drawable.s1)
                .artistName("Sidhu Moose Wala | The Kidd").trackId(1).build()
        )
        tracks.add(
            Track.Builder().trackName("Ajj Kal Ve").trackUrl("Ajj_Kal_Ve.mp3")
                .trackImage(R.drawable.s2)
                .artistName("Sidhu Moosewala").trackId(2).build()
        )
        tracks.add(
            Track.Builder().trackName("Bambiha Bole").trackUrl("Bambiha_Bole.mp3")
                .trackImage(R.drawable.s3)
                .artistName("Sidhu Moosewala | Amrit Maan").trackId(3).build()
        )
        tracks.add(
            Track.Builder().trackName("Bitch Im Back").trackUrl("Bitch_Im_Back.mp3")
                .trackImage(R.drawable.s4)
                .artistName("Sidhu Moosewala").trackId(4).build()
        )
        tracks.add(
            Track.Builder().trackName("Built Different").trackUrl("Built_Different.mp3")
                .trackImage(R.drawable.s5)
                .artistName("Sidhu Moosewala").trackId(5).build()
        )
        tracks.add(
            Track.Builder().trackName("Dear Mama").trackUrl("Dear_Mama.mp3")
                .trackImage(R.drawable.s6)
                .artistName("Sidhu Moosewala").trackId(6).build()
        )
        tracks.add(
            Track.Builder().trackName("Dhakka").trackUrl("Dhakka.mp3").trackImage(R.drawable.s7)
                .artistName("Sidhu Moosewala | Afsana Khan").trackId(7).build()
        )
        tracks.add(
            Track.Builder().trackName("G Shit").trackUrl("G_Shit.mp3").trackImage(R.drawable.s8)
                .artistName("Sidhu Moosewala | Blockboi Twitch").trackId(8).build()
        )
        tracks.add(
            Track.Builder().trackName("Me And My Girlfriend").trackUrl("Me_And_My_Girlfriend.mp3")
                .trackImage(R.drawable.s9)
                .artistName("Sidhu Moosewala").trackId(9).build()
        )
        tracks.add(
            Track.Builder().trackName("Power").trackUrl("Power.mp3").trackImage(R.drawable.s10)
                .artistName("Sidhu Moosewala | The Kidd").trackId(10).build()
        )
        tracks.add(
            Track.Builder().trackName("Regret").trackUrl("Regret.mp3").trackImage(R.drawable.s11)
                .artistName("Sidhu Moosewala | The Kidd").trackId(11).build()
        )
        tracks.add(
            Track.Builder().trackName("Sohne Lagde").trackUrl("Sohne_Lagde.mp3")
                .trackImage(R.drawable.s12)
                .artistName("Sidhu Moosewala | The PropheC").trackId(12).build()
        )
        tracks.add(
            Track.Builder().trackName("The Last Ride").trackUrl("The_Last_Ride.mp3")
                .trackImage(R.drawable.s13)
                .artistName("Sidhu Moosewala").trackId(13).build()
        )
        tracks.add(
            Track.Builder().trackName("Tibeyan Da Putt").trackUrl("Tibeyan_Da_Putt.mp3")
                .trackImage(R.drawable.s14)
                .artistName("Sidhu Moosewala | The Kidd").trackId(14).build()
        )
        tracks.add(
            Track.Builder().trackName("Unfuckwithable").trackUrl("Unfuckwithable.mp3")
                .trackImage(R.drawable.s15)
                .artistName("Sidhu Moosewala | Afsana Khan").trackId(15).build()
        )
    }

    /**
     * Retrieves a list of all tracks in the repository.
     *
     * @return a list of [Track] objects.
     */
    override fun getTrackList(): List<Track> {
        return tracks
    }
}