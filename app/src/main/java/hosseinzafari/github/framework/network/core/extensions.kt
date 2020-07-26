package network.core

import network.helper.InterceptorOffline
import network.helper.InterceptorOnline
import network.helper.OfflineInterceptor
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.net.URL

/*
    @created in 05/05/2020 - 3:42 PM
    @project Retrofit_startup2
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

// pulbic methods for easy access
fun retrofitBuilder(baseUri: String , block: CRetrofitBuilder.()->Unit): Retrofit {
    val retrofitBuilder = CRetrofitBuilder()
    retrofitBuilder.uri.mBaseUri = baseUri
    retrofitBuilder.block()
    return retrofitBuilder.build()
}


fun retrofitBuilder(baseUri: URL, block: CRetrofitBuilder.()->Unit): Retrofit{
    val retrofitBuilder = CRetrofitBuilder()
    retrofitBuilder.uri.mBaseURL = baseUri
    retrofitBuilder.block()
    return retrofitBuilder.build()
}


fun retrofitBuilder(baseUri: HttpUrl, block: CRetrofitBuilder.()->Unit): Retrofit{
    val retrofitBuilder = CRetrofitBuilder()
    retrofitBuilder.uri.mBaseHttpUrl = baseUri
    retrofitBuilder.block()
    return retrofitBuilder.build()
}



// lambda functions using by dsl
fun httpClientOriginal(block:OkHttpClient.Builder.()->Unit)  = OkHttpClient.Builder().apply(block)


fun converter(block:()-> Converter.Factory) = block.invoke()


fun httpClient(block: COkHttpBuilder.()->Unit)  = COkHttpBuilder().apply(block).build()


// interceptors
fun interceptorOffline(
    maxStaleForOffline: Int = 60 * 60 * 24 * 30,
    isOfflineInternet: OfflineInterceptor
) = InterceptorOffline(maxStaleForOffline, isOfflineInternet).getInterceptor()


fun interceptorOffline(
    customInterceptor: Interceptor
) = InterceptorOffline(customInterceptor).getInterceptor()


fun interceptorOnline(
    maxAgeForOnline: Int = 5 * 60
) = CNetworkInterceptor(InterceptorOnline(     maxAgeForOnline )     .getInterceptor())


fun interceptorOnline(
    customInterceptor: Interceptor
) = CNetworkInterceptor(InterceptorOnline(customInterceptor).getInterceptor())


// getApi replace create in retrofit
// common in java
fun <T> Retrofit.getApi(serviceClass: Class<T>) = create(serviceClass)

// using in kotlin
inline fun <reified T:Any> Retrofit.getApi() = create(T::class.java)

