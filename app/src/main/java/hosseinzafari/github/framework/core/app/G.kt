package hosseinzafari.github.framework.core.app

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import io.github.kbiakov.codeview.classifier.CodeProcessor

/*

@created in 25/06/2020 - 11:28 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

open class G : Application() {


    override fun onCreate() {
        super.onCreate()

        _context = applicationContext
        Fresco.initialize(applicationContext)
        CodeProcessor.init(this);
    }

    companion object {
        private lateinit var _context: Context
        var currentActivity: AppCompatActivity? = null
        var currentFragment: Fragment? = null
        val layoutInflater: LayoutInflater by lazy {
            LayoutInflater.from(getContext())
        }


        fun getContext(): Context = if (currentActivity != null) currentActivity!! else _context

    }

}