package network.helper

import android.content.Context
import hosseinzafari.github.retrofit_startup2.helper.G
import hosseinzafari.github.retrofit_startup2.lib.core.retrofitBuilder
import okhttp3.Cache
import java.io.File

/*

@created in 09/05/2020 - 1:21 PM
@project Retrofit_startup2
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

fun getDefaultCache(context: Context = G.mContext, size: Long = 10 * 1024 * 1024) = Cache(File(context.cacheDir.toURI()) , size)


fun getDefaultRetrofit(uri: String) =
    retrofitBuilder(uri){
        // TODO
    }
