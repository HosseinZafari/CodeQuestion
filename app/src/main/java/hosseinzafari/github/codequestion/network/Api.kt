package hosseinzafari.github.codequestion.ui.network

import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.struct.UserSignupModel
import hosseinzafari.github.codequestion.ui.struct.UserModel
import retrofit2.http.*

/*

@created in 10/07/2020 - 11:38 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface Api {

    @GET("best-user")
    suspend fun getBestUser(): List<UserModel>

    @GET("best-course")
    suspend fun getBestCourses(): ResponseStdModel

    @GET("all-course")
    suspend fun getAllCourses(): ResponseStdModel

    @GET("best-code")
    suspend fun getBestCode(): List<CodeModel>

    @GET("answers")
    suspend fun getAnswers(): ResponseStdModel

    @POST("signup")
    suspend fun signupUser(@Body userSignup: UserSignupModel): ResponseStdModel

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseStdModel


    @GET("rules")
    suspend fun getRules() : ResponseStdModel

    @FormUrlEncoded
    @POST("ask")
    suspend fun ask(
        @Field("title")  title: String ,
        @Field("text")   text: String ,
        @Field("type")   type: Int ,
        @Field("course") course: String): ResponseStdModel
}