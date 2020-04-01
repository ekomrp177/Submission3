package com.example.submission3.vievmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission3.parcelable.MovieParcelable
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MovieViewModel: ViewModel() {
    val listMovie = MutableLiveData<ArrayList<MovieParcelable>>()
    fun setMovie(){
        val listItems = ArrayList<MovieParcelable>()
        val apikey = "3bd6a93d27ad14d23a8b889ca53d26a0"
        val url = "https://api.themoviedb.org/3/discover/movie?api_key=$apikey&language=en-US"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                try {
                    val result = String(responseBody!!)
                    val responseObjects = JSONObject(result)
                    val list = responseObjects.getJSONArray("results")
                    for (i in 0 until list.length()){
                        val listApi = list.getJSONObject(i)
                        val listApiItems = MovieParcelable()
                        listApiItems.title = listApi.getString("title")
                        var tempPoster = listApi.getString("poster_path")
                        listApiItems.poster = "https://image.tmdb.org/t/p/w342/$tempPoster"
                        listApiItems.description = listApi.getString("overview")
                        listApiItems.rilis = listApi.getString("release_date")
                        listApiItems.score = listApi.getString("vote_average")
                        listApiItems.voter = listApi.getString("vote_count")
                        listItems.add(listApiItems)
                    }
                    listMovie.postValue(listItems)
                }catch (e: Exception){
                    Log.d("Exceptiion", e.message.toString())
                }
            }
            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d("onFailure", error?.message.toString())
            }
        })

    }
    fun getMovie(): LiveData<ArrayList<MovieParcelable>>{
        return listMovie
    }
}