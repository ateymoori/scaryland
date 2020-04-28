package magazine.scary.presentation.ui.movies_list


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.pixabay.utils.models.Success
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_dashboard.*
import magazine.scary.R
import magazine.scary.data.entities.MovieData
import magazine.scary.tools.utils.Cons
import javax.inject.Inject


class MoviesListFragment : Fragment(), MoviesListAdapter.MovieClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MoviesListViewModel
    @Inject
    lateinit var moviesAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MoviesListViewModel::class.java)

        viewModel.onViewCreated()

        moviesList.adapter = moviesAdapter.also { it.movieClickListener=this }

        observeVM()
    }

    private fun observeVM(){
        viewModel.imagesResults.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Success -> moviesAdapter.movies =
                    (it.data as List<MovieData>)
                else -> {
                }
            }
        })
    }

    override fun onMovieClicked(movie: MovieData) {
        Navigation.findNavController(moviesList).navigate(
            R.id.action_moviesListFragment_to_videoDetailsFragment
            ,
            bundleOf(Cons.ITEM_BUNDLE to movie)
        )
    }
}
