package davidcrotty.androiduitesting

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intent.*

/**
 * Created by DavidHome on 24/08/2016.
 */
class IntentActivity : AppCompatActivity() {

    companion object {
        val TEXT_KEY = "TEXT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        if(intent.hasExtra(TEXT_KEY)) {
            text_view.text = intent.getStringExtra(TEXT_KEY)
        }
    }
}