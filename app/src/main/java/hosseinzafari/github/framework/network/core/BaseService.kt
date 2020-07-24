package network.core

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import kotlin.properties.Delegates

/*

@created in 09/05/2020 - 11:32 AM
@project Retrofit_startup2
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/


object BaseService {
    var retrofit: Retrofit? by Delegates.vetoable(null , { _, _, _ -> true })


    inline fun <reified T> parseError(response: Response<*>): T? {
        val converter: Converter<ResponseBody, T> =
            retrofit!!.responseBodyConverter(T::class.java, T::class.java.annotations)
        val responseBody = response.errorBody() ?: return null
        val errorModel: T?
        try {
            errorModel = converter.convert(responseBody)
        } catch (e: IOException) {
            Log.i("LOG", "error in errorUtils Convert")
            return null
        }
        return errorModel
    }
}