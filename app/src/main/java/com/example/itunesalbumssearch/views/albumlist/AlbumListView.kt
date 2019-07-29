package com.example.itunesalbumssearch.views.albumlist

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import com.example.itunesalbumssearch.Album
import com.example.itunesalbumssearch.R
import kotlinx.android.synthetic.main.album_list.*

class AlbumListView : AppCompatActivity(), AlbumListContract.View {

    internal lateinit var context: Context
    internal lateinit var main: LinearLayout
    internal lateinit var txtNoAlbums: TextView
    internal lateinit var listAlbums: ShimmerRecyclerView

    private val dataAlbums = ArrayList<Album>()
    private var adapter: AlbumAdapter? = null

    internal var presenter: AlbumListPresenter

    init {
        presenter = AlbumListPresenter(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_list)

        context = this@AlbumListView

        main = album_list_main
        txtNoAlbums = findViewById(R.id.txtNoAlbums) as TextView
        listAlbums = findViewById(R.id.listAlbums) as ShimmerRecyclerView

        adapter = AlbumAdapter(context, dataAlbums)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        listAlbums.layoutManager = mLayoutManager
        listAlbums.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        listAlbums.itemAnimator = DefaultItemAnimator()
        listAlbums.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { //adding search
        menuInflater.inflate(R.menu.search, menu)
        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Search for Albums and nothing more!"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    fun search(strTerm: String) { //set list visible and get data
        val entity = "album"
        txtNoAlbums.visibility = View.GONE
        listAlbums.visibility = View.VISIBLE

        dataAlbums.clear()
        adapter!!.notifyDataSetChanged()

        setLoadingIndicator(true)
        listAlbums.postDelayed({ presenter.getAlbums(strTerm, entity) }, 2000)
    }

    override fun displayMessage(message: String) {
        setLoadingIndicator(false)
        Snackbar.make(main, message, Snackbar.LENGTH_LONG).show()
    }

    override fun setLoadingIndicator(isLoading: Boolean) {
        if (isLoading) {
            listAlbums.showShimmerAdapter()
        } else {
            listAlbums.hideShimmerAdapter()
        }
    }

    override fun displayAlbums(dataAlbums: List<Album>) { //refresh data
        setLoadingIndicator(false)
        this.dataAlbums.clear()
        this.dataAlbums.addAll(dataAlbums)
        adapter!!.notifyDataSetChanged()
    }
}
