package hosseinzafari.github.codequestion.ui.helper

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import hosseinzafari.github.framework.core.app.G
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.NotNull


/*

@created in 26/06/2020 - 9:50 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

fun log(msg: Any) = Log.i("Test", "$msg")


// Helper for Coroutine Kotiln
suspend fun <T> io(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.IO) { block(this) }
suspend fun <T> ui(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.Main) { block(this) }
suspend fun <T> cpuCore(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.Default) { block(this) }
suspend fun <T> uiReverse(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.Unconfined) { block(this) }


// YoYo
fun View.anim(techniques: Techniques, duration: Long = 300, repeat: Long = 0){
    YoYo.with(Techniques.SlideInLeft)
        .duration(300)
        .repeat(0)
        .playOn(this)
}


fun goActivity(@NotNull clazz: Class<out Activity>?) {
    G.currentActivity!!.startActivity(Intent(G.currentActivity, clazz))
}

fun goActivityWithFinish(@NotNull clazz: Class<out Activity>?) {
    goActivity(clazz)
    G.currentActivity!!.finish()
}
