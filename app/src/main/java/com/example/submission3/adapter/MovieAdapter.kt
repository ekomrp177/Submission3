package com.example.submission3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission3.R
import com.example.submission3.parcelable.MovieParcelable
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallback
    }
    interface OnItemClickCallBack {
        fun onItemClicked(movie: MovieParcelable)
    }

    private val mData = ArrayList<MovieParcelable>()
    fun setData(items: ArrayList<MovieParcelable>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieParcelable){
            with(itemView){
                tv_namemovie.text = movie.title
                Picasso.get().load(movie.poster).into(img_photomovie)
                tv_descriptionmovie.text = movie.description
                tv_scoremovie.text = "${movie.score}% from ${movie.voter}"
            }
            itemView.setOnClickListener { onItemClickCallBack?.onItemClicked(movie) }
        }

    }
}