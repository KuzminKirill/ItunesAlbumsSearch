package com.example.itunesalbumssearch.views.albumdetail

import com.example.itunesalbumssearch.TrackModel
import com.example.itunesalbumssearch.api.ServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class AlbumDetailPresenter(private val songListView: AlbumDetailContact.View) : AlbumDetailContact.Presenter {

    override fun getAlbumTracks(id: String, entity: String) { //get data
        val service = ServiceFactory.instance
        val trackModelCall = service.getAlbumTracks(id, entity)
        trackModelCall.enqueue(object : Callback<TrackModel> {
            override fun onResponse(call: Call<TrackModel>, response: Response<TrackModel>) {
                val trackModel = response.body()
                if (trackModel.resultCount > 0) {
                    val res = trackModel.results.sortedBy { it.trackName } //sorting
                    songListView.displaySongList(res)
                } else {
                    songListView.displayMessage("No songs here, try something else")
                }
            }

            override fun onFailure(call: Call<TrackModel>, t: Throwable?) {
                songListView.displayMessage("Nothing to see here")

            }

        })
    }

}