package com.example.itunesalbumssearch.views.albumdetail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.itunesalbumssearch.R
import com.example.itunesalbumssearch.Track
import kotlinx.android.synthetic.main.song_item.view.*

class SongListAdapter internal constructor(private val songList: List<Track>) :
    RecyclerView.Adapter<SongListAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var row: LinearLayout
        var txtSongName: TextView

        init {
            row = view.song_item_row
            txtSongName = view.song_name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, ValueType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val song = songList[position]

        holder.txtSongName.text = song.trackName


    }

}