package magazine.scary.presentation.ui.container

import android.R.attr.duration
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.pixabay.utils.base.BaseActivity
import com.pixabay.utils.models.AudioModel
import com.pixabay.utils.models.AudioStatus
import com.pixabay.utils.rxbus.RxBus
import com.pixabay.utils.rxbus.RxEvent
import com.pixabay.utils.tools.log
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import magazine.scary.R
import magazine.scary.tools.utils.ImageLoader
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity : BaseActivity(isFullScreen = true) {


    private lateinit var mediaPlayer: MediaPlayer
    private var compositeDisposable = CompositeDisposable()
    lateinit var audio: AudioModel

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        timer()
        compositeDisposable.add(RxBus.listen(RxEvent.AudioPlay::class.java).subscribe({
            audio = it.audio
            audio.toString().log("play_audio 0 ")
            showAudioDetail()
        }, {
            it.toString().log("play_audio 1 ")
        }))
        compositeDisposable.add(RxBus.listen(RxEvent.AudioControl::class.java).subscribe({
            controlAudio(it.audioStatus)
        }, {
            it.toString().log("play_audio 2 ")
        }))

        playPause.setOnClickListener {
            if (mediaPlayer.isPlaying)
                controlAudio(AudioStatus.PAUSE)
            else
                controlAudio(AudioStatus.RESUME)
        }
        stop.setOnClickListener {
            controlAudio(AudioStatus.STOP)
        }

    }

    private fun showAudioDetail() {
        if (::audio.isInitialized) {
            audioTitle.text = audio.title
            audioSubTitle.text = audio.subTitle
            imageLoader.load(url = audio.image, imageView = audioImage)
        }
    }

    private fun timer() {
        compositeDisposable.add(io.reactivex.Observable.interval(1, TimeUnit.SECONDS)
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { everySecond() })
    }

    private fun everySecond() {
        if (::mediaPlayer.isInitialized) {
            musicSeekBar.setProgress((mediaPlayer.currentPosition / 1000).toFloat())
            milliToStringFormat(mediaPlayer.duration).log("mp3_ ")
            milliToStringFormat(mediaPlayer.currentPosition).log("mp3_ ")
            time.text = milliToStringFormat(mediaPlayer.duration)
        }
    }

    private fun milliToStringFormat(milliSeconds:Int):String{
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(milliSeconds.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(milliSeconds.toLong()) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(
                            milliSeconds.toLong()
                        )
                    )
        )
    }
    private fun controlAudio(audioStatus: AudioStatus) {
        if (::audio.isInitialized) {
            audioStatus.toString().log("play_audio 3 ")
            when (audioStatus) {
                AudioStatus.PLAY -> {
                    controlAudio(AudioStatus.STOP)
                    musicPlayer.visibility = View.VISIBLE
                    val url = audio.audioURL
                    mediaPlayer = MediaPlayer().apply {
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        setDataSource(url)
                        prepare()
                        start()
                    }
                    musicSeekBar.max = (mediaPlayer.duration / 1000).toFloat()
                    playPause.setImageDrawable(getDrawable(R.drawable.ic_pause))
                }
                AudioStatus.RESUME -> {
                    audioStatus.toString().log("play_audio resum3 ")
                    mediaPlayer.start()
                    mediaPlayer.seekTo(mediaPlayer.currentPosition)
                    playPause.setImageDrawable(getDrawable(R.drawable.ic_pause))
                }
                AudioStatus.PAUSE -> {
                    if (::mediaPlayer.isInitialized) {
                        mediaPlayer.pause()
                        playPause.setImageDrawable(getDrawable(R.drawable.ic_play))
                    }
                }
                AudioStatus.STOP -> {
                    if (::mediaPlayer.isInitialized) {
                        mediaPlayer.stop()
                    }
                    musicPlayer.visibility = View.GONE
                    playPause.setImageDrawable(getDrawable(R.drawable.ic_play))

                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
        controlAudio(AudioStatus.PAUSE)
    }

    fun toggleMenu() {
        if (!drawer.isDrawerOpen(GravityCompat.END))
            drawer.openDrawer(GravityCompat.END)
        else
            drawer.closeDrawer(GravityCompat.END)
    }
}
