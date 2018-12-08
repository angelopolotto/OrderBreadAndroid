package app.mobify.orderbread.feature.utils.views

import android.app.Activity
import android.content.Intent

inline fun <reified T: Activity> Activity.cutomStartActivityForResult(requestCode: Int) {
    val intent = Intent(this, T::class.java)
    startActivityForResult(intent, requestCode)
}