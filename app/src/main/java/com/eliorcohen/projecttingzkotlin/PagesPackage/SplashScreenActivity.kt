package com.eliorcohen.projecttingzkotlin.PagesPackage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.eliorcohen.projecttingzkotlin.ModelsPackage.TotalModel
import com.eliorcohen.projecttingzkotlin.R
import com.eliorcohen.projecttingzkotlin.UtilsPackage.ApplicationUtil
import elior.com.infrastructurekotlin.RetrofitPackage.GetDataService
import elior.com.infrastructurekotlin.RoomPackage.RoomFavorites
import elior.com.infrastructurekotlin.RoomPackage.RoomViewModelFavorites
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SplashScreenActivity : AppCompatActivity() {

    private var mRoomViewModelFavorites: RoomViewModelFavorites? = null
    private val mArrayListRoomData = arrayListOf<RoomFavorites>()
    private var disposable: Disposable? = null
    private val client by lazy {
        GetDataService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        init()
        getData()
    }

    private fun init() {
        mRoomViewModelFavorites =
            ApplicationUtil.application?.let { RoomViewModelFavorites(it) }
    }

    private fun getData() {
        disposable =
            client.getAllData("application/json", "/json/movies.json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        run {
                            addDataToRoom(result)
                        }
                    },
                    { error -> Log.e(TAG, error.message + " ") })
    }

    private fun addDataToRoom(result: ArrayList<TotalModel>) {
        for (i in 0 until result.size) {
            val roomFavorites = RoomFavorites(
                result[i].title.toString(),
                result[i].image.toString(),
                result[i].rating!!,
                result[i].releaseYear!!,
                result[i].genre!!,
            )
            mArrayListRoomData.add(roomFavorites)
        }

        mArrayListRoomData.let { mRoomViewModelFavorites!!.insertData(it) }

        Log.i(TAG, mArrayListRoomData.size.toString())

        startActivity(Intent(this, MovieListActivity::class.java))
    }

    companion object {
        private const val TAG = "checkTingz"
    }

}
