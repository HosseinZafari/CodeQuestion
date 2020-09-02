package hosseinzafari.github.codequestion.ui.data.repository

import hosseinzafari.github.codequestion.data.datasource.remote.CodeRemoteDataSource
import hosseinzafari.github.codequestion.data.main.CodeMain

/*
    @created in 25/07/2020 - 12:23 PM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

class CodeRepository : CodeMain{
    private val codeRemote = CodeRemoteDataSource()

    override suspend fun getBestCode()= codeRemote.getBestCode()

    override suspend fun getAllCode(category: Int) = codeRemote.getAllCode(category)

    override suspend fun changeScore(isAdd: Boolean, codeId: Int) = codeRemote.changeScore(isAdd , codeId)

    override suspend fun addCode(
        title: String,
        text: String,
        source: String
    ) = codeRemote.addCode(title, text , source)

}