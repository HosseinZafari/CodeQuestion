package hosseinzafari.github.codequestion.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import hosseinzafari.github.codequestion.data.repository.SharedPrefRepository
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.framework.core.app.G

/*

@created in 13/08/2020 - 03:22 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class MainViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {

    val sharedPrefRepository = SharedPrefRepository()


    fun prepareTokenInMemory() {
        sharedPrefRepository.getToken().observe(G.currentActivity!!) {
            log("TOKEN #1 " + it + "")
            SaveInMemory.token = it
            log("TOKEN #2 " + SaveInMemory.token + "")
        }
    }

    fun prepareUserJsonInfo(){
        sharedPrefRepository.getUserJson().observe(G.currentActivity!!) {
            log("USERINFO #1 " + it + "")
            if(it != null) {
                SaveInMemory.userJsonInfo = it
            }
            log("USERINFO #2 " + SaveInMemory.userJsonInfo + "")
        }
    }
}