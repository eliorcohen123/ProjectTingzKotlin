package com.eliorcohen.projecttingzkotlin.PagesPackage

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.eliorcohen.projecttingzkotlin.R
import elior.com.infrastructurekotlin.RoomPackage.RoomFavorites
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity(), View.OnClickListener {

    private var mRoomFavorites: RoomFavorites? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        init()
        initListeners()
        setData()
    }

    private fun init() {
        mRoomFavorites =
            intent.extras!!.getSerializable(getString(R.string.movie_list_data)) as RoomFavorites?
    }

    private fun initListeners() {
        btnBackMovieList.setOnClickListener(this)
    }

    private fun setData() {
        Glide.with(this).load(mRoomFavorites?.image).into(imageMovieDetails)
        titleMovieDetails.text = mRoomFavorites?.title
        ratingMovieDetails.text = mRoomFavorites?.rating.toString()
        releaseYearMovieDetails.text = mRoomFavorites?.releaseYear.toString()
        val sb = StringBuilder()
        for (str in mRoomFavorites?.genre!!) {
            sb.append(str).append(",")
        }
        genreMovieDetails.text = sb.delete(sb.length - 1, sb.length)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btnBackMovieList) {
            onBackPressed()
        }
    }

}
