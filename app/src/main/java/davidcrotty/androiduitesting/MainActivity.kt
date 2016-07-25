package davidcrotty.androiduitesting

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by DavidHome on 24/07/2016.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        action_button.setOnClickListener({
            button_result_text.text = "Hello"
        })
    }
}