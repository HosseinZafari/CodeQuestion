package hosseinzafari.github.codequestion.data.repository

import hosseinzafari.github.codequestion.data.datasource.remote.AskRemoteDataSource
import hosseinzafari.github.codequestion.data.main.AskMain

/*

@created in 17/08/2020 - 10:25 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AskRepository : AskMain {

    private val askDataSource = AskRemoteDataSource()

    override suspend fun ask(
        title: String,
        text: String,
        type: Int,
        course: String
    ) = askDataSource.ask(title , text , type , course)

    override suspend fun answers() = askDataSource.answers()

}