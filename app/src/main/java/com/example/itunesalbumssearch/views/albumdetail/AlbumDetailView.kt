package com.example.itunesalbumssearch.views.albumdetail

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.itunesalbumssearch.R
import com.example.itunesalbumssearch.Track
import kotlinx.android.synthetic.main.album_detail.*
import java.lang.Exception

class AlbumDetailView : AppCompatActivity(), AlbumDetailContact.View {



    internal lateinit var context: Context
    internal lateinit var main: LinearLayout
    internal lateinit var imgArtwork: ImageView
    internal lateinit var txtArtistName: TextView
    internal lateinit var txtCount: TextView
    internal lateinit var listSongs: RecyclerView

    private val dataTracks = ArrayList<Track>()
    private var adapter: SongListAdapter? = null

    internal var presenter: AlbumDetailPresenter = AlbumDetailPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_detail)

        context = this@AlbumDetailView

        main = album_detail_main
        imgArtwork = imgArtworkDetail
        txtArtistName = artist_name_detail
        txtCount = count_detail

        adapter = SongListAdapter(dataTracks)

        listSongs = song_list


        try {
            val entity = "song"
            val id = intent.getIntExtra("albumId", 0)
            val name = intent.getStringExtra("albumName")
            val artistName = intent.getStringExtra("artistName")
            val price = intent.getDoubleExtra("albumPrice", 0.0)
            val artworkURL = intent.getStringExtra("artworkURLs")
            val trackCount = intent.getIntExtra("trackCount", 0)
            displayAlbum(id, name, artistName, price, artworkURL, trackCount)


            val mLayoutManager = LinearLayoutManager(applicationContext)
            listSongs.layoutManager = mLayoutManager
            //listSongs.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            listSongs.itemAnimator = DefaultItemAnimator()
            listSongs.adapter = adapter
            listSongs.postDelayed({presenter.getAlbumTracks(id.toString(), entity)}, 200)
            displaySongList(dataTracks)

        } catch (e: Exception) {
            displayMessage("Problem while getting info, sorry")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun displayMessage(message: String) {
        Snackbar.make(main, message, Snackbar.LENGTH_LONG).show()
    }

    override fun displayAlbum(id: Int, name: String, artistName: String, price: Double, artwork: String, trackCount: Int) {
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = name
        }

        val artworkUrl = artwork
        Glide.with(context).load(artworkUrl).into(imgArtwork)

        txtArtistName.text = "Artist name: " + artistName
        txtCount.text = trackCount.toString() + " songs"


        displaySongList(dataTracks)

    }

    override fun displaySongList(dataTracks: List<Track>) {
        this.dataTracks.clear()
        this.dataTracks.addAll(dataTracks)
        adapter!!.notifyDataSetChanged()
    }
}
