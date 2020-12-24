package com.eliorcohen.projecttingzkotlin.PagesPackage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.eliorcohen.projecttingzkotlin.AdapterPackage.AdapterMovieList
import com.eliorcohen.projecttingzkotlin.R
import com.eliorcohen.projecttingzkotlin.UtilsPackage.ApplicationUtil
import com.eliorcohen.projecttingzkotlin.UtilsPackage.ItemDecoration
import elior.com.infrastructurekotlin.RoomPackage.RoomViewModelFavorites
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    private var mRoomViewModelFavorites: RoomViewModelFavorites? = null
    private var mAdapterMovieList: AdapterMovieList? = null
    private var mItemDecoration: ItemDecoration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        init()
        initRV()
    }

    private fun init() {
        mRoomViewModelFavorites =
            ApplicationUtil.application?.let { RoomViewModelFavorites(it) }

        mAdapterMovieList = AdapterMovieList(this)
    }

    private fun initRV() {
        if (mItemDecoration == null) {
            mItemDecoration = ItemDecoration(30)
            recyclerViewMovieList.addItemDecoration(mItemDecoration!!)
        }
        recyclerViewMovieList.layoutManager = LinearLayoutManager(this)
        recyclerViewMovieList.adapter = mAdapterMovieList

        putData()
    }

    private fun putData() {
        mRoomViewModelFavorites!!.getAllData().observe(this, { roomFavorites ->
            mAdapterMovieList?.setData(roomFavorites)

            Log.i(TAG, roomFavorites.size.toString() + " ")
        })
    }

    companion object {
        private const val TAG = "checkTingz"
    }

}
