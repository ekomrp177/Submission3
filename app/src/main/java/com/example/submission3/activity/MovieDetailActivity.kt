package com.example.submission3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission3.R
import com.example.submission3.parcelable.MovieParcelable
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<MovieParcelable>(EXTRA_DATA)
        name_view.setText(movie.title)
        Picasso.get().load(movie.poster).into(img_view)
        description.setText(movie.description)
        rilis.setText(movie.rilis)
        score.setText("${movie.score} (${movie.voter})")
    }
}
