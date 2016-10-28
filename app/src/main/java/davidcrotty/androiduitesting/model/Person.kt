package davidcrotty.androiduitesting.model

import com.google.gson.annotations.SerializedName

/**
 * Created by DavidHome on 28/10/2016.
 */
class Person() {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("height")
    var height: String? = null
}