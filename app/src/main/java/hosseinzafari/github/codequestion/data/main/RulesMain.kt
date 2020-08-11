package hosseinzafari.github.codequestion.data.main

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.struct.ResponseStdModel

/*

@created in 11/08/2020 - 02:15 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

interface RulesMain {

    suspend fun getRules() : LiveData<ResponseStdModel>

}