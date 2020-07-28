package hosseinzafari.github.codequestion.ui.data.main

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.struct.CourseModel

/*

@created in 25/07/2020 - 12:11 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface CourseMain {

    suspend fun getCourses(): LiveData<List<CourseModel>>

}