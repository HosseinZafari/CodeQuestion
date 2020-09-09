package hosseinzafari.github.codequestion.ui.network

import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import network.core.getApi
import network.core.httpClient
import network.core.retrofitBuilder
import network.helper.getLogginInterceptor
import network.util.m
import retrofit2.converter.gson.GsonConverterFactory

/*
    @created in 10/07/2020 - 10:28 AM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

val retrofit by lazy {
    retrofitBuilder("http://192.168.1.5/code-question/api/v1/") {
        +GsonConverterFactory.create()
        +true
        +httpClient {
            +connect time 2.m
            +call time 1.m
            +read time 1.m

            interceptor(getLogginInterceptor())

            +interceptor {
                val oldRequest = it.request()
                val newRequest = oldRequest.newBuilder()

                // Send Token If Already Exsits
                if(!SaveInMemory.token.isNullOrBlank()){
                    newRequest
                        .addHeader("token" ,  SaveInMemory.token!!)
                        .addHeader("Accent" , "application/json")
                }

                // Combine Requests
                newRequest.method(oldRequest.method() , oldRequest.body())
                it.proceed(newRequest.build())
            }
        }
    }
}

val api by lazy {
    retrofit.getApi<Api>()
}

