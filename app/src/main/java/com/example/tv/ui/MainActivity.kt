package com.example.tv.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tv.R
import com.example.tv.ui.movieList.MovieListFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_movie_list.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MovieListFragment())
            .commit()

//      SWIPE TO REFRESH - not working :/
//        setSupportActionBar(toolbar)
//        var swipeLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
//        swipeLayout.setOnRefreshListener { this }
    }

    override fun onBackPressed() {
        tellFragments()
        super.onBackPressed()
    }

    private fun tellFragments(){
        var fragments: List<Fragment> = supportFragmentManager.fragments
        fragments.forEach { f ->
            if (f is BaseFragment) f.onBackPressed()
        }
    }
}