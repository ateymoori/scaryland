package magazine.scary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_description.*
import javax.inject.Inject


class MovieDescriptionFragment @Inject constructor() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_description, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
//        arguments?.let {
//            movie = it.getParcelable(Cons.ITEM_BUNDLE)!!
//        }
    }

    lateinit var text: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content.text = text
    }
}
