package davidcrotty.androiduitesting

import android.app.Application

/**
 * Created by DavidHome on 23/08/2016.
 */
class App : Application() {

    companion object {
        var isRunningUITest : Boolean = false
    }
}