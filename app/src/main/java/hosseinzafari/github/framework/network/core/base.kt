package network.core

import android.util.Log
import network.util.CTimeUnit
import network.util.detectTime
import network.util.mergeTimeAndUnit
import network.util.s
import okhttp3.*
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.net.InetAddress
import java.net.Proxy
import java.net.ProxySelector
import java.net.URL
import java.time.Duration
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.net.SocketFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/*
    @created in 04/05/2020 - 2:50 PM
    @project Retrofit_startup2
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

@DslMarker
annotation class CRetrofitBuilderMarker

@CRetrofitBuilderMarker
class ConverterDslMarker

@CRetrofitBuilderMarker
class CallAdapterDslMarker

@CRetrofitBuilderMarker
class ExecutorDslMarker


@CRetrofitBuilderMarker
data class CRetrofitURI(
    var mBaseUri: String? = null ,
    var mBaseHttpUrl: HttpUrl? = null ,
    var mBaseURL: URL? = null
)


@CRetrofitBuilderMarker
data class CNetworkInterceptor(
    var  interceptor: Interceptor
)

@CRetrofitBuilderMarker
data class CProxyAuthenticator(
    var  authenticator: Authenticator
)

@CRetrofitBuilderMarker
sealed class CTimeHelper(var time: CTimeUnit?, var duration: Duration?)

class CCallTimeOut(time: CTimeUnit? = null, duration: Duration? = null) : CTimeHelper(time, duration)
class CReadTimeOut(time: CTimeUnit? = null, duration: Duration? = null) : CTimeHelper(time, duration)
class CWriteTimeOut(time: CTimeUnit? = null, duration: Duration? = null) : CTimeHelper(time, duration)
class CConncetTimeOut(time: CTimeUnit? = null, duration: Duration? = null) : CTimeHelper(time, duration)
class CPingInterval(time: CTimeUnit? = null, duration: Duration? = null) : CTimeHelper(time, duration)


@CRetrofitBuilderMarker
data class CHttpTime(
    var connectTimeOut: CConncetTimeOut? = null,
    var readTimeOut: CReadTimeOut? = null,
    var writeTimeOut: CWriteTimeOut? = null,
    var callTimeOut: CCallTimeOut? = null
)




@CRetrofitBuilderMarker
class CRetrofitBuilder{
    val uri = CRetrofitURI()
    var doValidate: Boolean = false
    var callbackExecutor: Executor? = null

    private var httpClientBuilder: OkHttpClient.Builder? = null
    private var callAdapterFactory: MutableList<CallAdapter.Factory>? = null
    private var converterFactory: MutableList<Converter.Factory>? = null


    private fun prepareConveter(){
        if(converterFactory == null){
            converterFactory = mutableListOf()
        }
    }


    private fun prepareCallAdapter(){
        if(callAdapterFactory == null){
            callAdapterFactory = mutableListOf()
        }
    }


    operator fun CallAdapter.Factory.unaryPlus() {
        prepareCallAdapter()
        callAdapterFactory?.add(this)
    }


    operator fun CallAdapter.Factory.plus(call: CallAdapter.Factory) {
        prepareCallAdapter()

        callAdapterFactory?.add(this)
        callAdapterFactory?.add(call)
    }


    operator fun Converter.Factory.unaryPlus() {
        prepareConveter()
        converterFactory?.add(this)
    }


    operator fun Converter.Factory.plus(converter: Converter.Factory) : MutableList<Converter.Factory>{
        prepareConveter()
        converterFactory?.add(this)
        converterFactory?.add(converter)

        return converterFactory!!
    }


    operator fun Executor.unaryPlus() {
        callbackExecutor = this
    }


    operator fun OkHttpClient.Builder.unaryPlus() {
        httpClientBuilder = this
    }


    operator fun String.unaryPlus(){
        uri.mBaseUri = this
    }


    operator fun URL.unaryPlus(){
        uri.mBaseURL = this
    }


    operator fun HttpUrl.unaryPlus(){
        uri.mBaseHttpUrl = this
    }


    operator fun Boolean.unaryPlus(){
        doValidate = this
    }


    inline fun converter(block: ConverterDslMarker.()->Converter.Factory): Converter.Factory{
        val marker =
            ConverterDslMarker()
        return marker.block()
    }

    
    fun converter(converter: Converter.Factory){
        prepareConveter()
        converterFactory?.add(converter)
    }


    fun callAdapter(callsAdapter: CallAdapter.Factory){
        prepareCallAdapter()
        callAdapterFactory?.add(callsAdapter)
    }


    inline fun callAdapter(callAdapter: CallAdapterDslMarker.() -> CallAdapter.Factory): CallAdapter.Factory{
        val marker =
            CallAdapterDslMarker()
        return marker.callAdapter()
    }

    
    inline fun callbackExecutor(block: ExecutorDslMarker.()->Executor): Executor{
        return ExecutorDslMarker()
            .block()
    }


    fun addAllCallsAdapter(callsAdapter: List<CallAdapter.Factory>){
        callAdapterFactory = callsAdapter.toMutableList()
    }


    fun addAllConverters(converters: List<Converter.Factory>){
        this.converterFactory = converters.toMutableList()
    }


    fun httpClient(httpClientBuilder: OkHttpClient.Builder){
        this.httpClientBuilder = httpClientBuilder
    }


    fun build(): Retrofit{
        val builder = Retrofit.Builder()

        builder.validateEagerly(doValidate)

        uri.apply {
            mBaseUri?.also {
                builder.baseUrl(it)
            }
            mBaseURL?.also {
                builder.baseUrl(it)
            }
            mBaseHttpUrl?.also {
                builder.baseUrl(it)
            }
        }
        
        converterFactory?.also { 
            for (converter in it){
                builder.addConverterFactory(converter)
            }
        }
        
        callAdapterFactory?.also { 
            for (call in it){
                builder.addCallAdapterFactory(call)
            }
        }
        
        callbackExecutor?.also { 
            builder.callbackExecutor(it)
        }
        
        httpClientBuilder?.also {
            builder.client(it.build())
        }

        Log.d("Test" , "converter size ${converterFactory?.size}:  call size ${callAdapterFactory?.size}")
        return builder.build()
    }
}



@CRetrofitBuilderMarker
class COkHttpBuilder {
    
    private var networkInterceptors: MutableList<CNetworkInterceptor>? = null
    private var interceptors: MutableList<Interceptor>? = null
    private var pingInterval: CPingInterval? = null
    private var sslSocketFactory: SSLSocketFactory? = null
    private var trustManager: X509TrustManager? = null
    private val timesOut = CHttpTime()

    var connectionSpecs: List<ConnectionSpec>? = null
    var protocols: List<Protocol>? = null
    var cache: Cache? = null
    var authenticator: Authenticator? = null
    var certificatePinner: CertificatePinner? = null
    var connectionPool: ConnectionPool? = null
    var retryOnConnectionFailure: Boolean? = null
    var cookieJar: CookieJar? = null
    var dispatcher: Dispatcher? = null
    var dns: Dns? = null
    var eventListener: EventListener? = null
    var eventListenerFactory: EventListener.Factory? = null
    var followRedirect: Boolean? = null
    var followSslRedirect: Boolean? = null
    var hostNameVerifier: HostnameVerifier? = null
    var proxy: Proxy? = null
    var proxySelector: ProxySelector? = null
    var proxyAuthenticator: Authenticator ? = null
    var socketFactory: SocketFactory? = null

    val connect : Int  = 1
    val call    : Int  = 2
    val read    : Int  = 3
    val write   : Int  = 4
    val ping    : Int  = 5

    private fun prepareNetworkInterceptor(){
        if (networkInterceptors == null) {
            networkInterceptors = mutableListOf()
        }    
    }


    private fun prepareInterceptor(){
        if (interceptors == null) {
            interceptors = mutableListOf()
        }    
    }


    fun networkInterceptor(networkInterceptor: Interceptor){
        prepareNetworkInterceptor()
        networkInterceptors?.add(
            CNetworkInterceptor(
                networkInterceptor
            )
        )
    }


    inline fun networkInterceptor(crossinline block:(Interceptor.Chain)->Response) =
        CNetworkInterceptor(
            Interceptor { block(it) })
    
    
    fun interceptor(interceptor: Interceptor){
        prepareInterceptor()
        interceptors?.add(interceptor)
    }


    fun connectTimeout(unit: TimeUnit = TimeUnit.SECONDS, time: Long) {
        timesOut.connectTimeOut =
            CConncetTimeOut(
                time = mergeTimeAndUnit(
                    time,
                    unit
                )
            )
    }


    fun connectTimeout(duration: Duration) {
        timesOut.connectTimeOut =
            CConncetTimeOut(
                duration = duration
            )
    }

    
    fun callTimeout(unit: TimeUnit = TimeUnit.SECONDS, time: Long) {
        timesOut.callTimeOut =
            CCallTimeOut(
                time = mergeTimeAndUnit(time, unit)
            )
    }


    fun callTimeout(duration: Duration) {
        timesOut.callTimeOut = CCallTimeOut(duration = duration)
    }

    
    fun readTimeout(unit: TimeUnit = TimeUnit.SECONDS, time: Long) {
        timesOut.readTimeOut =
            CReadTimeOut(
                time = mergeTimeAndUnit(time, unit)
            )
    }


    fun readTimeout(duration: Duration) {
        timesOut.readTimeOut =
            CReadTimeOut(duration = duration)
    }


    fun writeTimeout(unit: TimeUnit = TimeUnit.SECONDS, time: Long) {
        timesOut.writeTimeOut  =
            CWriteTimeOut(time = mergeTimeAndUnit(time, unit))
    }


    fun writeTimeout(duration: Duration) {
        timesOut.writeTimeOut  = CWriteTimeOut(duration = duration)
    }


    fun authenticator(authenticator: Authenticator){
        this.authenticator = authenticator
    }


    fun certificatePinner(certificatePinner: CertificatePinner){
        this.certificatePinner = certificatePinner
    }


    fun connectionPool(connectionPool: ConnectionPool){
        this.connectionPool = connectionPool
    }


    fun connectionSpecs(connectionSpecs: MutableList<ConnectionSpec>){
        this.connectionSpecs = connectionSpecs
    }


    fun retryOnConnectionFailure(retryOnConnectionFailure: Boolean){
        this.retryOnConnectionFailure = retryOnConnectionFailure
    }


    fun cookieJar(cookieJar: CookieJar){
        this.cookieJar= cookieJar
    }


    fun dispatcher(dispatcher: Dispatcher){
        this.dispatcher = dispatcher
    }


    fun dns(dns: Dns){
        this.dns = dns
    }


    fun dns(block: (String) -> MutableList<InetAddress>) = Dns { hostname -> block(hostname) }


    fun eventListener(eventListener: EventListener) {
        this.eventListener = eventListener
    }


    fun eventListenerFactory(eventListenerFactory: EventListener.Factory) {
        this.eventListenerFactory = eventListenerFactory
    }


    fun eventListenerFactory(block: (Call) -> EventListener) = EventListener.Factory { call -> block(call) }


    fun authenticator(block: (Route?, Response) -> Request?) =
        Authenticator { route, response -> block(route, response) }


    fun followRedirect(followRedirect: Boolean){
        this.followRedirect = followRedirect
    }


    fun followSslRedirect(followSslRedirect: Boolean){
        this.followSslRedirect = followSslRedirect
    }


    fun hostNameVerifier(hostNameVerifier: HostnameVerifier){
        this.hostNameVerifier = hostNameVerifier
    }


    fun hostNameVerifier(block: (String?, SSLSession?) -> Boolean) =
        HostnameVerifier { hostname, session -> block(hostname, session) }


    fun pingInterval(duration: Duration){
        this.pingInterval =
            CPingInterval(duration = duration)
    }


    fun protocols(protocols: MutableList<Protocol>){
        this.protocols = protocols
    }


    fun proxy(proxy: Proxy){
        this.proxy = proxy
    }


    fun proxySelector(proxySelector: ProxySelector){
        this.proxySelector = proxySelector
    }


    fun proxyAuthenticator(proxyAuthenticator: Authenticator){
        this.proxyAuthenticator = proxyAuthenticator
    }


    fun proxyAuthenticator(block: (Route?, Response) -> Request?) =
        CProxyAuthenticator(
            Authenticator { route, response -> block(route, response) })


    fun socketFactory(socketFactory: SocketFactory){
        this.socketFactory = socketFactory
    }


    fun sslSocketFactory(sslSocketFactory: SSLSocketFactory , trustManager: X509TrustManager){
        this.sslSocketFactory = sslSocketFactory
        this.trustManager = trustManager
    }


    fun interceptors() = interceptors?.toList()


    fun networkInterceptors() =
        networkInterceptors?.mapIndexed{ _, it -> it.interceptor }


    fun onlineInterceptor(interceptor: CNetworkInterceptor) = networkInterceptor(interceptor.interceptor)


    fun offlineInterceptor(interceptor: Interceptor)  = interceptor(interceptor)


    fun logginInterceptor(interceptor: Interceptor)  = interceptor(interceptor)


    inline fun interceptor(crossinline block: (Interceptor.Chain)->Response) = Interceptor{ block(it) }
    
    
    fun cache(cache: Cache){
        this.cache = cache
    }
    
    operator fun CNetworkInterceptor.unaryPlus(){
        this@COkHttpBuilder.prepareNetworkInterceptor()
        this@COkHttpBuilder.networkInterceptors?.add(this)
        Log.d("Test" , "networkInterceptor size ${this@COkHttpBuilder.networkInterceptors?.size}")
    }

    operator fun Interceptor.unaryPlus(){
        this@COkHttpBuilder.prepareInterceptor()
        this@COkHttpBuilder.interceptors?.add(this)
        Log.d("Test" , "interceptors size ${this@COkHttpBuilder.interceptors?.size}")
    }
    
    operator fun Cache.unaryPlus(){
        this@COkHttpBuilder.cache = this 
    }


    operator fun Authenticator.unaryPlus(){
        this@COkHttpBuilder.authenticator = this
    }

    operator fun CProxyAuthenticator.unaryPlus(){
        this@COkHttpBuilder.proxyAuthenticator = this.authenticator
    }

    operator fun Dns.unaryPlus(){
        this@COkHttpBuilder.dns = this
    }


    operator fun HostnameVerifier.unaryPlus(){
        this@COkHttpBuilder.hostNameVerifier = this
    }

    @JvmName("connectionSpec")
    operator fun List<ConnectionSpec>.unaryPlus(){
        this@COkHttpBuilder.connectionSpecs = this
    }

    @JvmName("protocol")
    operator fun List<Protocol>.unaryPlus(){
        this@COkHttpBuilder.protocols = this
    }

    operator fun CertificatePinner.unaryPlus(){
        this@COkHttpBuilder.certificatePinner = this
    }

    operator fun ConnectionPool.unaryPlus(){
        this@COkHttpBuilder.connectionPool = this
    }

    operator fun CookieJar.unaryPlus(){
        this@COkHttpBuilder.cookieJar = this
    }

    operator fun EventListener.unaryPlus(){
        this@COkHttpBuilder.eventListener = this
    }

    operator fun EventListener.Factory.unaryPlus(){
        this@COkHttpBuilder.eventListenerFactory = this
    }

    operator fun Proxy.unaryPlus(){
        this@COkHttpBuilder.proxy = this
    }

    operator fun ProxySelector.unaryPlus(){
        this@COkHttpBuilder.proxySelector = this
    }

    operator fun SocketFactory.unaryPlus(){
        this@COkHttpBuilder.socketFactory = this
    }


    operator fun CTimeHelper.unaryPlus(){
        when(this){
            is CConncetTimeOut ->this@COkHttpBuilder.timesOut.connectTimeOut = this
            is CCallTimeOut ->this@COkHttpBuilder.timesOut.callTimeOut = this
            is CReadTimeOut ->this@COkHttpBuilder.timesOut.readTimeOut = this
            is CWriteTimeOut ->this@COkHttpBuilder.timesOut.writeTimeOut = this
            is CPingInterval ->this@COkHttpBuilder.pingInterval = this
        }
    }


    infix fun Int.time(timeUnit: CTimeUnit): CTimeHelper {
        return when(this){
            this@COkHttpBuilder.connect-> CConncetTimeOut(
                timeUnit
            )
            this@COkHttpBuilder.call-> CCallTimeOut(
                timeUnit
            )
            this@COkHttpBuilder.write-> CWriteTimeOut(
                timeUnit
            )
            this@COkHttpBuilder.read-> CReadTimeOut(
                timeUnit
            )
            this@COkHttpBuilder.ping-> CPingInterval(
                timeUnit
            )
            else -> throw IllegalArgumentException("no found type of timeOut")
        }
    }


    infix fun Int.time(timeUnit: Number) : CTimeHelper = time(timeUnit.s)


    fun build(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        if(timesOut.connectTimeOut != null){
            if (timesOut.connectTimeOut!!.duration != null){
                builder.connectTimeout(timesOut.connectTimeOut!!.duration!!)
            }

            if (timesOut.connectTimeOut!!.time != null){
                detectTime(timesOut.connectTimeOut!!.time!!
                ) { number, unit ->
                    builder.connectTimeout(number.toLong(), unit)
                }
            }
        }

        if(timesOut.callTimeOut != null){
            if (timesOut.callTimeOut!!.duration != null){
                builder.callTimeout(timesOut.callTimeOut!!.duration!!)
            }

            if (timesOut.callTimeOut!!.time != null){
                detectTime(timesOut.callTimeOut!!.time!!
                ) { number, unit ->
                    builder.callTimeout(number.toLong(), unit)
                }
            }
        }

        if(timesOut.readTimeOut != null){
            if (timesOut.readTimeOut!!.duration != null){
                builder.readTimeout(timesOut.readTimeOut!!.duration!!)
            }
            if (timesOut.readTimeOut!!.time != null){
                detectTime(timesOut.readTimeOut!!.time!!
                ) { number, unit ->
                    builder.readTimeout(number.toLong(), unit)
                }
            }
        }

        if(timesOut.writeTimeOut != null){
            if (timesOut.writeTimeOut!!.duration != null) {
                builder.writeTimeout(timesOut.writeTimeOut!!.duration!!)
            }
            if (timesOut.writeTimeOut!!.time != null){
                detectTime(
                    timesOut.writeTimeOut!!.time!!
                ) { number, unit ->
                    builder.writeTimeout(number.toLong(), unit)
                }
            }
        }


        if (sslSocketFactory != null && trustManager != null) {
            builder.sslSocketFactory(sslSocketFactory!!, trustManager!!)
        }

        socketFactory?.also {
            builder.socketFactory(it)
        }

        proxyAuthenticator?.also {
            builder.proxyAuthenticator(it)
        }

        proxy?.also {
            builder.proxy(it)
        }

        proxySelector?.also{
            builder.proxySelector(it)
        }

        protocols?.also{
            builder.protocols(it)
        }

        pingInterval?.also { it ->
            it.duration?.also {duration->
                builder.pingInterval(duration)
            }

            it.time?.also {timeUnit->
                detectTime(
                    timeUnit
                ) { num, unit ->
                    builder.pingInterval(num.toLong(), unit)
                }
            }
        }

        dns?.also {
            builder.dns(it)
        }

        dispatcher?.also {
            builder.dispatcher(it)
        }

        hostNameVerifier?.also {
            builder.hostnameVerifier(it)
        }

        followRedirect?.also {
            builder.followRedirects(it)
        }

        followSslRedirect?.also {
            builder.followSslRedirects(it)
        }

        eventListener?.also {
            builder.eventListener(it)
        }

        eventListenerFactory?.also {
            builder.eventListenerFactory(it)
        }

        cookieJar?.also {
            builder.cookieJar(it)
        }

        retryOnConnectionFailure?.also {
            builder.retryOnConnectionFailure(it)
        }

        networkInterceptors?.also {
            it.forEach {item->
                builder.addNetworkInterceptor(item.interceptor)
            }
        }

        interceptors?.also {
            it.forEach {item->
                builder.addInterceptor(item)
            }
        }

        connectionSpecs?.also {
            builder.connectionSpecs(it)
        }

        connectionPool?.also {
            builder.connectionPool(it)
        }

        certificatePinner?.also {
            builder.certificatePinner(it)
        }

        cache?.also {
            builder.cache(it)
        }

        authenticator?.also {
            builder.authenticator(it)
        }


        return builder
    }
}
