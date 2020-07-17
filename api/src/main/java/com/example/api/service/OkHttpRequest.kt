package com.example.api.service

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class OkHttpRequest(client: OkHttpClient) {

    companion object {
        const val MOVIE_DB_TOKEN = "98faa6158edefff64629c43f40e15dff";
    }

    internal var client = OkHttpClient()

    init {
        this.client = client
    }

    fun GET(url: String, callback: Callback): Call {
        val request = Request.Builder()
            .url(url + MOVIE_DB_TOKEN)
            .build()
        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }
}