package hosseinzafari.github.framework.core.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import hosseinzafari.github.codequestion.ui.util.UiUtil
import hosseinzafari.github.framework.core.app.G

/*

@created in 25/06/2020 - 11:45 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

open class GAppCompatActivity : AppCompatActivity() {

    protected var uiUtil = UiUtil(this)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        G.currentActivity = this
    }


}