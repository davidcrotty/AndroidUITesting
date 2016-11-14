package davidcrotty.androiduitesting

/**
 * Created by DavidHome on 14/11/2016.
 */
interface ProgressListener {
    fun onProgressShown()
    fun onProgressDismissed()
    fun setProgressListenerWith(listener: ProgressListener)
}