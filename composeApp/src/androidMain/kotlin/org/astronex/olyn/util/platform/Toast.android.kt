package org.astronex.olyn.util.platform

// androidMain
import android.widget.Toast
import org.astronex.olyn.applicationContext

actual fun showToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

