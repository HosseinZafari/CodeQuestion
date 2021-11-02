package hosseinzafari.github.framework.core.app

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import hosseinzafari.github.codequestion.data.Room.AppDb
import hosseinzafari.github.codequestion.data.Room.AppDbBuilder
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

        __context = applicationContext
        __database = AppDbBuilder.getInstance(getContext())
        Fresco.initialize(applicationContext)
        CodeProcessor.init(this);
    }

    companion object {
        private lateinit var __context: Context
        var currentActivity: AppCompatActivity? = null
        var currentFragment: Fragment? = null
        val layoutInflater: LayoutInflater by lazy {
            LayoutInflater.from(getContext())
        }

        // Database
        private var __database: AppDb? = null
        val database: AppDb get() = __database!!

        const val DB_NAME = "db_codequesion"


        fun getContext(): Context = if (currentActivity != null) currentActivity!! else __context
    }

}