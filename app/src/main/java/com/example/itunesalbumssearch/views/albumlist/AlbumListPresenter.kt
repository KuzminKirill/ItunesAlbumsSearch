package com.example.itunesalbumssearch.views.albumlist

import com.example.itunesalbumssearch.AlbumModel
import com.example.itunesalbumssearch.api.ServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class AlbumListPresenter(private val albumListView: AlbumListContract.View) : AlbumListContract.Presenter {

    override fun getAlbums(term: String, entity: String) { // get data
        val service = ServiceFactory.instance
        val albumModelCall = service.getAlbums(term, entity)
        albumModelCall.enqueue(object : Callback<AlbumModel> {
            override fun onResponse(call: Call<AlbumModel>, response: Response<AlbumModel>) {
                val albumModel = response.body()
                if (albumModel.resultCount > 0) {
                    val res = albumModel.results.sortedBy { it.collectionName } //sotring results by name
                    albumListView.displayAlbums(res)
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