package com.dawinder.musicplayer_jetpackcompose.repository

import com.dawinder.musicplayer_jetpackcompose.R
import com.dawinder.musicplayer_jetpackcompose.model.Song
import javax.inject.Inject


class SongRepositoryImpl @Inject constructor() : SongRepository {
    private val songs = mutableListOf<Song>()

    init {
        // Initialize songs here or load from a data source
        createSongs()
    }

    private fun createSongs() {
        // Add songs to the repository
        songs.add(
            Song.Builder().songName("Song 1").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 1").songId(1).build()
        )
        songs.add(
            Song.Builder().songName("Song 2").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 2").songId(2).build()
        )
        songs.add(
            Song.Builder().songName("Song 3").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 3").songId(3).build()
        )
        songs.add(
            Song.Builder().songName("Song 4").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 4").songId(4).build()
        )
        songs.add(
            Song.Builder().songName("Song 5").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 5").songId(5).build()
        )
        songs.add(
            Song.Builder().songName("Song 6").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 6").songId(6).build()
        )
        songs.add(
            Song.Builder().songName("Song 7").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 7").songId(7).build()
        )
        songs.add(
            Song.Builder().songName("Song 8").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 8").songId(8).build()
        )
        songs.add(
            Song.Builder().songName("Song 9").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 9").songId(9).build()
        )
        songs.add(
            Song.Builder().songName("Song 10").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 10").songId(10).build()
        )
        songs.add(
            Song.Builder().songName("Song 11").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 11").songId(11).build()
        )
        songs.add(
            Song.Builder().songName("Song 12").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 12").songId(12).build()
        )
        songs.add(
            Song.Builder().songName("Song 13").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 13").songId(13).build()
        )
        songs.add(
            Song.Builder().songName("Song 14").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 14").songId(14).build()
        )
        songs.add(
            Song.Builder().songName("Song 15").songUrl("").songImage(R.mipmap.ic_launcher)
                .singerName("Artist 15").songId(15).build()
        )
    }

    override fun getSongList(): List<Song> {
        return songs
    }
}