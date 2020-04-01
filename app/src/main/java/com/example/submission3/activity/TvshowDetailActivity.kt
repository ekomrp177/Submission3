package com.example.submission3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submission3.R
import com.example.submission3.parcelable.TVShowParcelable
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tvshow_detail.*

class TvshowDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvshow_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvshow = intent.getParcelableExtra<TVShowParcelable>(EXTRA_DATA)
        name_view.text = tvshow.title
        Picasso.get().load(tvshow.poster).into(img_view)
        description.text = tvshow.description
        rilis.text = tvshow.rilis
        score.text = "${tvshow.score} (${tvshow.voter})"
    }
}
