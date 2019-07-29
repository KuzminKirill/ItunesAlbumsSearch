package com.example.itunesalbumssearch.albumlist

import com.example.itunesalbumssearch.AlbumModel
import com.example.itunesalbumssearch.api.ServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class AlbumListPresenter(private val albumListView: AlbumListContract.View) : AlbumListContract.Presenter {

    override fun getAlbums(term: String, entity: String) {
        val service = ServiceFactory.instance
        val albumModelCall = service.getAlbums(term, entity)
        albumModelCall.enqueue(object : Callback<AlbumModel> {
            override fun onResponse(call: Call<AlbumModel>, response: Response<AlbumModel>) {
                val albumModel = response.body()
                if (albumModel.resultCount > 0) {
                    albumListView.displayAlbums(albumModel.results)
                } else {
                    albumListView.displayMessage("No album found, Try again.")
                }
            }

            override fun onFailure(call: Call<AlbumModel>, t: Throwable) {
                albumListView.displayMessage("No albums found, Try again.")
            }
        })
    }
}