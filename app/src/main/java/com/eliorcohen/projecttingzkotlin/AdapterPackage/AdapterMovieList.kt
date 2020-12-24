package com.eliorcohen.projecttingzkotlin.AdapterPackage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.eliorcohen.projecttingzkotlin.PagesPackage.MovieDetailsActivity
import com.eliorcohen.projecttingzkotlin.R
import elior.com.infrastructurekotlin.RoomPackage.RoomFavorites
import kotlinx.android.synthetic.main.adapter_movie_list.view.*

class AdapterMovieList internal constructor(
    private val context: Context,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mRoomFavorites: List<RoomFavorites>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.adapter_movie_list, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(context, mRoomFavorites!![position])

        setFadeAnimation(holder.itemView)
    }

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, roomFavorites: RoomFavorites) {
            itemView.releaseYearAdapterMovieList.text = roomFavorites.releaseYear.toString()
            itemView.linearLayoutAdapterMovieList.setOnClickListener {
                val intentToMovieList =
                    Intent(context, MovieDetailsActivity::class.java)
                intentToMovieList.putExtra(
                    context.getString(R.string.movie_list_data), roomFavorites
                )
                context.startActivity(intentToMovieList)
            }
        }
    }

    fun setData(roomFavorites: List<RoomFavorites>) {
        this.mRoomFavorites = roomFavorites
        notifyDataSetChanged()
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1500
        view.startAnimation(anim)
    }

    override fun getItemCount() = if (mRoomFavorites != null) mRoomFavorites!!.size else 0
}

