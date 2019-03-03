package com.opalynskyi.cleanmovies.app.mainscreen.movies.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.opalynskyi.cleanmovies.R
import com.opalynskyi.cleanmovies.app.CleanMoviesApplication
import com.opalynskyi.cleanmovies.app.mainscreen.movies.adapter.MovieItem
import com.opalynskyi.cleanmovies.app.mainscreen.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.movies_fragment_layout.*
import timber.log.Timber
import javax.inject.Inject

class AllMoviesFragment : Fragment(), AllMoviesContract.View {

    @Inject
    lateinit var presenter: AllMoviesContract.Presenter
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movies_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CleanMoviesApplication.instance.getAllMoviesComponent().inject(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MoviesAdapter(mutableListOf())
        recyclerView?.adapter = adapter
        presenter.bind(this)
        presenter.getMovies()
        swipeRefreshLayout.setOnRefreshListener { presenter.getMovies() }
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun showEmptyState() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorMsg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMovies(movies: List<MovieItem>) {
        Timber.d("List of movies: ${movies.size}")
        adapter?.refreshList(movies.toMutableList())
    }


    companion object {
        @JvmStatic
        fun newInstance() = AllMoviesFragment()
    }
}