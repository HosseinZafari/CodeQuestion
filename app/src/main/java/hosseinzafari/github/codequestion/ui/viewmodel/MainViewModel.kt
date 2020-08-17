package hosseinzafari.github.codequestion.ui.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import hosseinzafari.github.codequestion.data.repository.TokenRepository
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.framework.core.app.G

/*

@created in 13/08/2020 - 03:22 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class MainViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {

    val tokenRepository = TokenRepository()


    fun prepareTokenInMemory() {
        tokenRepository.getToken().observe(G.currentActivity!! , Observer {
            log("TOKEN #1 " + it + "")
            SaveInMemory.token = it + ""
            log("TOKEN #2 " + SaveInMemory.token + "")
        })
    }

}