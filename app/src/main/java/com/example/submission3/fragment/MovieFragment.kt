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
import com.example.submission3.adapter.MovieAdapter
import com.example.submission3.parcelable.MovieParcelable
import com.example.submission3.vievmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private lateinit var movieAdapter : MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieAdapter = MovieAdapter()
        movieAdapter.notifyDataSetChanged()

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = movieAdapter

        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
        progressBar.visibility = View.VISIBLE
        movieViewModel.setMovie()

        movieViewModel.getMovie().observe(this, Observer {
            MovieParcelable ->
            progressBar.visibility = View.INVISIBLE
            if (MovieParcelable != null){
                movieAdapter.setData(MovieParcelable)
            }
        })
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallBack{
            override fun onItemClicked(movie: MovieParcelable) {
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.EXTRA_DATA, movie)
                startActivity(intent)
            }
        })


    }

}
