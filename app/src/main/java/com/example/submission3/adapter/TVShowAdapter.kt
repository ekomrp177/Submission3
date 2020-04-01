package com.example.submission3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submission3.R
import com.example.submission3.parcelable.TVShowParcelable
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tvshow.view.*

class TVShowAdapter: RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {
    private var onItemClickCallBack: OnItemClickCallBack? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallback
    }
    interface OnItemClickCallBack {
        fun onItemClicked(tvShow: TVShowParcelable)
    }


    private val mData = ArrayList<TVShowParcelable>()
    fun setData(items: ArrayList<TVShowParcelable>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVShowViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_tvshow, parent, false)
        return TVShowViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class TVShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(tvShow: TVShowParcelable) {
            with(itemView){
                tv_nametvshow.text = tvShow.title
                Picasso.get().load(tvShow.poster).into(img_phototvshow)
                tv_descriptiontvshow.text = tvShow.description
                tv_scoretvshow.text = "${tvShow.score}% from ${tvShow.voter}"
            }
            itemView.setOnClickListener { onItemClickCallBack?.onItemClicked(tvShow) }
        }

    }

}