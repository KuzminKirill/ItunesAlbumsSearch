package com.example.itunesalbumssearch.albumlist

import android.content.Context
import android.content.Intent
import com.example.itunesalbumssearch.Album
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.itunesalbumssearch.R
import com.example.itunesalbumssearch.albumdetail.AlbumDetailView
import java.io.Serializable


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
        Glide.with(context).load(artworkUrl).into(holder.imgAlbumArtwork) //smth must be here

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
            row = view.findViewById(R.id.album_item_row) as LinearLayout
            imgAlbumArtwork = view.findViewById(R.id.artwork) as ImageView
            txtAlbumName = view.findViewById(R.id.album_name) as TextView
            txtArtistName = view.findViewById(R.id.artist_name) as TextView
            txtCount = view.findViewById(R.id.track_count) as TextView
            row.setOnClickListener {
                val detail = Intent(context, AlbumDetailView::class.java)
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