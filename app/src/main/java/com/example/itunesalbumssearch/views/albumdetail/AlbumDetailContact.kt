package com.example.itunesalbumssearch.views.albumdetail

import com.example.itunesalbumssearch.Track

class AlbumDetailContact{

    interface View {
        fun displayMessage(message: String)

        fun displayAlbum(id: Int, name: String, artistName: String, price: Double, artwork: String, trackCount: Int)

        fun displaySongList(dataTracks: List<Track>)
    }

    interface Presenter{
        fun getAlbumTracks(id: String, entity: String)
    }
}