package hosseinzafari.github.codequestion.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import hosseinzafari.github.codequestion.data.repository.TokenRepository
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
    private val tokenRepository = TokenRepository()

    fun getAllCodes(category: Int) = liveData {
        emit(Resource.loading())
        try {
            val codes = io { codeRepository.getAllCode(category) }
            emit(Resource.success(codes.value))
        } catch (e: Exception) {
            emit(Resource.error())
        }
    }

    fun changeScore(isAdd: Boolean , codeId: Int) = liveData {
        emit(Resource.loading())
        try {
            val state = io { codeRepository.changeScore(isAdd , codeId) }
            emit(Resource.success(state.value))
        } catch (e: Exception) {
            emit(Resource.error())
        }
    }

    fun addCode(title: String , text: String , source: String) = liveData {
        emit(Resource.loading())
        try {
            val result = io { codeRepository.addCode(title , text , source) }
            emit(Resource.success(result.value))
        } catch (e: Exception) {
            emit(Resource.error(e.message.toString()))
        }
    }

    fun getToken() = tokenRepository.getToken()
}