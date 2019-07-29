package com.example.itunesalbumssearch.api

import com.example.itunesalbumssearch.AlbumModel
import com.example.itunesalbumssearch.TrackModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface APIService { //retrofit usage. 2 GET request to server
    @GET("search") // -> get albums
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getAlbums(@Query("term") term: String, @Query ("entity") entity: String): Call<AlbumModel> //example:  search?term=somename&entity=album

    @GET("lookup") // -> get songs of album
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getAlbumTracks(@Query ("id") id: String, @Query("entity") entity: String): Call<TrackModel> //example:  lookup?id=1234&entity=song
}