package magazine.scary.presentation.ui.movie_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import magazine.scary.presentation.ui.movie_feedbacks.MovieFeedBacksFragment

class MoviePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MovieFeedBacksFragment.get()
            else -> MovieFeedBacksFragment.get()
        }
    }

    override fun getCount(): Int {
        return 1
    }
}