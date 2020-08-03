package hosseinzafari.github.codequestion.ui.network

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
    retrofitBuilder("http://192.168.1.6/code-question/api/v1/") {
        +GsonConverterFactory.create()
        +true
        +httpClient {
            +connect time 2.m
            +call time 1.m
            +read time 1.m
            interceptor(getLogginInterceptor())
        }
    }
}

val api by lazy {
    retrofit.getApi<Api>()
}

