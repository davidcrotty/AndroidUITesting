package davidcrotty.androiduitesting.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import davidcrotty.androiduitesting.R
import kotlinx.android.synthetic.main.complex_view.view.*

/**
 * Created by DavidHome on 26/07/2016.
 */
class ComplexView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    val VIEW_STATE_VISIBLE = 1
    val VIEW_STATE_INVISIBLE = 2
    var viewStateHolder: Int = 1

    init {
        View.inflate(context, R.layout.complex_view, this)
    }

    fun getSomeState() : Int {
        return 1
    }

    fun hideSpinner() {
        radio_button.visibility = View.GONE
    }

    fun showSpinner() {
        radio_button.visibility = View.VISIBLE
    }
}