package com.example.submission3.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.submission3.R
import com.example.submission3.activity.MovieDetailActivity
import com.example.submission3.activity.TvshowDetailActivity
import com.example.submission3.adapter.TVShowAdapter
import com.example.submission3.parcelable.TVShowParcelable
import com.example.submission3.vievmodel.TVShowViewModel
import kotlinx.android.synthetic.main.fragment_tvshow.*

/**
 * A simple [Fragment] subclass.
 */
class TvshowFragment : Fragment() {

    private lateinit var tvShowAdapter: TVShowAdapter
    private lateinit var tvShowViewModel: TVShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvShowAdapter = TVShowAdapter()
        tvShowAdapter.notifyDataSetChanged()

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = tvShowAdapter

        tvShowViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TVShowViewModel::class.java)
        progressBar.visibility = View.VISIBLE

        tvShowViewModel.setTVShow()

        tvShowViewModel.getTVShow().observe(this, Observer {
            TVShowParcelable ->
            progressBar.visibility = View.INVISIBLE
            if (TVShowParcelable != null){
                tvShowAdapter.setData(TVShowParcelable)
            }
        })
        tvShowAdapter.setOnItemClickCallback(object : TVShowAdapter.OnItemClickCallBack{
            override fun onItemClicked(tvShow: TVShowParcelable) {
                val intent = Intent(context, TvshowDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.EXTRA_DATA, tvShow)
                startActivity(intent)
            }
        })

    }

}
