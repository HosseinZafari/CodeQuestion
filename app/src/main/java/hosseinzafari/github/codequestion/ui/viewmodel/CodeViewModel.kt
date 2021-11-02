package hosseinzafari.github.codequestion.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import hosseinzafari.github.codequestion.data.repository.SharedPrefRepository
import hosseinzafari.github.codequestion.struct.ResponseStdModel
import hosseinzafari.github.codequestion.ui.data.repository.CodeRepository
import hosseinzafari.github.codequestion.ui.helper.io
import hosseinzafari.github.codequestion.ui.ui.util.Resource

/*

@created in 23/08/2020 - 04:28 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class CodeViewModel : ViewModel(){

    private val codeRepository  = CodeRepository()
    private val tokenRepository = SharedPrefRepository()

    fun getAllCodes(category: Int) = liveData {
        emit(Resource.loading())
        try {
            val codes = io { codeRepository.getAllCode(category) }
            Log.i("Tehran" , "value "  )
            emit(Resource.success(codes.value))
        } catch (e: Exception) {
            emit(Resource.error<ResponseStdModel>())
        }
    }

    fun changeScore(isAdd: Boolean , codeId: Int) = liveData {
        emit(Resource.loading())
        try {
            val state = io { codeRepository.changeScore(isAdd , codeId) }
            Log.i("Tehran" , "value " + state.value)
            emit(Resource.success(state.value))
        } catch (e: Exception) {
            emit(Resource.error<ResponseStdModel>())
        }
    }

    fun addCode(title: String , text: String , source: String) = liveData {
        emit(Resource.loading())
        try {
            val result = io { codeRepository.addCode(title , text , source) }
            emit(Resource.success(result.value))
        } catch (e: Exception) {
            emit(Resource.error<ResponseStdModel>(e.message.toString()))
        }
    }

    fun getToken() = tokenRepository.getToken()
}