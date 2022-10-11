package io.element.android.x

import android.app.Application
import com.airbnb.mvrx.Mavericks
import io.element.android.x.sdk.matrix.MatrixInstance

class ElementXApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MatrixInstance.init(this)
        Mavericks.initialize(this)
    }
}