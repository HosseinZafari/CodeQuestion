package hosseinzafari.github.codequestion.ui.helper

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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