package hosseinzafari.github.codequestion.ui.network

import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.struct.UserSignupModel
import hosseinzafari.github.codequestion.ui.struct.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/*

@created in 10/07/2020 - 11:38 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface Api {

    @GET("best-user")
    suspend fun getBestUser() :  List<UserModel>

    @GET("course")
    suspend fun getCourses(): List<CourseModel>

    @GET("best-code")
    suspend fun getBestCode(): List<CodeModel>


    @POST("signup")
    suspend fun signupUser(@Body userSignup: UserSignupModel): ResponseStdModel
}