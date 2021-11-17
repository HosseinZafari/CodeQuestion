package hosseinzafari.github.codequestion.data.main

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.struct.ResponseStdModel

/*

@created in 17/08/2020 - 10:20 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface AskMain {


    suspend fun ask(title: String , text: String , type: Int , course: String): LiveData<ResponseStdModel>

    suspend fun answers(page: Int): LiveData<ResponseStdModel>
    
    suspend fun returned(questionId: Int , returnedType: Int): LiveData<ResponseStdModel>
}