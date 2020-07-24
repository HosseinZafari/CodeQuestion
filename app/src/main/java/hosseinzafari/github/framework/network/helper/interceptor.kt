package network.helper

import hosseinzafari.github.codequestion.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/*

@created in 06/05/2020 - 2:08 PM
@project Retrofit_startup2
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/


typealias OfflineInterceptor = (()->Boolean)

fun getLogginInterceptor() = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG) {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

class InterceptorOffline {
    private var maxStaleOffline: Int = 0
    private var offlineInterceptor: OfflineInterceptor? = null
    private var customInterceptor: Interceptor? = null

    constructor(customInterceptor: Interceptor){
        this.customInterceptor = customInterceptor
    }

    constructor(maxStaleOffline: Int ,  isOfflineInternt: OfflineInterceptor){
        this.maxStaleOffline = maxStaleOffline
        this.offlineInterceptor = isOfflineInternt
    }

    fun getInterceptor() =
        if (customInterceptor != null)
            customInterceptor!!
        else
            Interceptor { chain ->
                var originalRequest = chain.request()
                if (offlineInterceptor!!.invoke()) {
                    originalRequest = originalRequest.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStaleOffline")
                        .removeHeader("Pragma")
                        .build()
                }
                chain.proceed(originalRequest)
            }
}

class InterceptorOnline {
    private var maxAgeOnline: Int = 0
    private var customInterceptor: Interceptor? = null

    constructor(maxAgeOnline: Int) {
        this.maxAgeOnline = maxAgeOnline
    }

    constructor(customInterceptor: Interceptor) {
        this.customInterceptor = customInterceptor
    }

    fun getInterceptor() =
        if (customInterceptor != null)
            customInterceptor!!
        else
            Interceptor { chain ->
                val originalResponse = chain.proceed(chain.request())
                originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAgeOnline")
                    .removeHeader("Pragma")
                    .build()
            }

}