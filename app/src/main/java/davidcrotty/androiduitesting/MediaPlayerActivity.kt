package davidcrotty.androiduitesting

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_media_player.*

/**
 * Created by DavidHome on 14/11/2016.
 */
class MediaPlayerActivity : AppCompatActivity(), ProgressListener {

    var _listener : ProgressListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        load_button.setOnClickListener {
            video_view.setVideoURI(Uri.parse("http://techslides.com/demos/sample-videos/small.mp4"))
            _listener?.onProgressShown()
            video_view.start()
            video_view.setOnPreparedListener {
                Toast.makeText(this, "Prepared", Toast.LENGTH_SHORT).show()
                video_loaded_text.text = "Loaded"
                _listener?.onProgressDismissed()
            }
        }
    }

    override fun onProgressShown() {
    }

    override fun onProgressDismissed() {
    }

    override fun setProgressListenerWith(listener: ProgressListener) {
        _listener = listener
    }
}