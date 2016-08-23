package davidcrotty.androiduitesting

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by DavidHome on 24/07/2016.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if(isInstrumentedRun()) {
            progress_bar.indeterminateDrawable = ContextCompat.getDrawable(this, R.drawable.ic_launcher)
        }


        action_button.setOnClickListener({
            button_result_text.text = "Hello"
        })

        visibility_button.setOnClickListener({
            toggle_switch.isChecked = true
        })

        toggle_switch.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {
                visibility_button.visibility = View.INVISIBLE
            } else {
                visibility_button.visibility = View.VISIBLE
            }
        }

        hide_spinner_button.setOnClickListener({
            complex_view.hideSpinner()
        })
    }

    fun isInstrumentedRun(): Boolean {
        val result: Boolean
        try {
            classLoader.loadClass(
                    "davidcrotty.androiduitesting.MainActivityTest")
            result = true
        } catch (e: Exception) {
            result = false
        }

        return result
    }
}