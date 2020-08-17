package hosseinzafari.github.codequestion.data.repository

import hosseinzafari.github.codequestion.data.datasource.local.TokenLocalDataSource
import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import hosseinzafari.github.codequestion.data.main.TokenMain

/*

@created in 07/08/2020 - 11:02 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class TokenRepository : TokenMain {

    private val tokenLocal = TokenLocalDataSource()

    override fun getToken() = tokenLocal.getToken()

    override suspend fun setToken(token: String)  {
        SaveInMemory.token = token
        tokenLocal.setToken(token)
    }
}