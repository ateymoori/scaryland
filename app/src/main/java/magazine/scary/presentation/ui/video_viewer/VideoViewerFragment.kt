package magazine.scary.presentation.ui.video_viewer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_video_viewer.back
import kotlinx.android.synthetic.main.fragment_video_viewer.trailer
import magazine.scary.R
import magazine.scary.domain.entities.VideoPureEntity
import magazine.scary.tools.utils.Cons

class VideoViewerFragment : Fragment() {

    lateinit var video: VideoPureEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        arguments?.let {
            video = it.getParcelable(Cons.ITEM_BUNDLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_viewer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back.setOnClickListener { activity?.onBackPressed() }

        lifecycle.addObserver(trailer)

        trailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = video.url ?: ""
                youTubePlayer.loadVideo(videoId, 0f)
                youTubePlayer.pause()
            }
        })

    }

}
