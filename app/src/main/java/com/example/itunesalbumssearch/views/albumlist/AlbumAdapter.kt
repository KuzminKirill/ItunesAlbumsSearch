package com.example.itunesalbumssearch.views.albumlist

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.itunesalbumssearch.Album
import com.example.itunesalbumssearch.R
import com.example.itunesalbumssearch.views.albumdetail.AlbumDetailView
import kotlinx.android.synthetic.main.album_item.view.*


class AlbumAdapter internal constructor(var context: Context, private val albumList: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return albumList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val album = albumList[position]

        val artworkUrl = album.artworkUrl100
        Glide.with(context).load(artworkUrl).into(holder.imgAlbumArtwork)

        holder.txtAlbumName.text = album.collectionName
        holder.txtArtistName.text = album.artistName
        holder.txtCount.text = album.trackCount.toString() + "tracks"
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var row: LinearLayout
        var imgAlbumArtwork: ImageView
        var txtAlbumName: TextView
        var txtArtistName: TextView
        var txtCount: TextView

        init {
            row = view.album_item_row
            imgAlbumArtwork = view.artwork
            txtAlbumName = view.album_name
            txtArtistName = view.artist_name
            txtCount = view.track_count
            row.setOnClickListener {
                val detail = Intent(context, AlbumDetailView::class.java)
                //there is a huge problem. i want to cast album object to activity by "putExtra"
                //but data class is not serializable in kotlin. i tried to do something but
                //i don't want to do a new GET request to server every time i click on item
                //i have to do request to get songs list of album and don't want to do 2 requests
                //so, i cast fields with information that i need
                detail.putExtra("albumId", albumList[adapterPosition].collectionId)
                detail.putExtra("albumName", albumList[adapterPosition].collectionName)
                detail.putExtra("artistName", albumList[adapterPosition].artistName)
                detail.putExtra("albumPrice", albumList[adapterPosition].collectionPrice)
                detail.putExtra("artworkURLs", albumList[adapterPosition].artworkUrl100)
                detail.putExtra("trackCount", albumList[adapterPosition].trackCount )
                context.startActivity(detail)
            }
        }
    }


}