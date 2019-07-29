package com.example.itunesalbumssearch.albumlist

import com.example.itunesalbumssearch.Album


class AlbumListContract {

    internal interface View {
        fun displayMessage(message: String)

        fun setLoadingIndicator(isLoading: Boolean)

        fun displayAlbums(dataAlbums: List<Album>)
    }

    interface Presenter {
        fun getAlbums(term: String, entity: String)
    }
}