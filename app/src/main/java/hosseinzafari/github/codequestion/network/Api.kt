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

    @GET("best-user.php")
    suspend fun getBestUser(): List<UserModel>

    @GET("best-course.php")
    suspend fun getBestCourses(): ResponseStdModel

    @GET("all-course.php")
    suspend fun getAllCourses(): ResponseStdModel

    @GET("best-code.php")
    suspend fun getBestCode(): List<CodeModel>

    @GET("answers.php")
    suspend fun getAnswers(): ResponseStdModel

    @GET("all-code.php")
    suspend fun getAllCodes(
        @Query("category") category: Int
    ): ResponseStdModel

    @GET("code-point.php")
    suspend fun changeScore(
        @Query("score") score: Int ,
        @Query("codeId") codeId: Int
    ): ResponseStdModel

    @POST("signup.php")
    suspend fun signupUser(@Body userSignup: UserSignupModel): ResponseStdModel

    @FormUrlEncoded
    @POST("add-code.php")
    suspend fun addCode(
        @Field("title") title: String ,
        @Field("text") text: String ,
        @Field("source") source: String
    ): ResponseStdModel

    @FormUrlEncoded
    @POST("login.php")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseStdModel


    @GET("rules.php")
    suspend fun getRules() : ResponseStdModel

    @FormUrlEncoded
    @POST("ask.php")
    suspend fun ask(
        @Field("title")  title: String ,
        @Field("text")   text: String ,
        @Field("type")   type: Int ,
        @Field("course") course: String): ResponseStdModel
    
    @FormUrlEncoded
    @POST("returned.php")
    suspend fun returned(
        @Field("returnedType") returnedType: Int ,
        @Field("questionId")   questionId: Int
    ): ResponseStdModel
}