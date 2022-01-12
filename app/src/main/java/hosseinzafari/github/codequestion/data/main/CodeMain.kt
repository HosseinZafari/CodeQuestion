package hosseinzafari.github.codequestion.data.main

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.struct.ResponseStdModel

interface CodeMain {

    suspend fun getBestCode() : LiveData<List<CodeModel>>

    suspend fun getAllCode(category :Int) : LiveData<ResponseStdModel>

    suspend fun changeScore(isAdd: Boolean, codeId: Int): LiveData<ResponseStdModel>

    suspend fun addCode(title: String , text: String , source: String) : LiveData<ResponseStdModel>

    suspend fun getAllPendingCode(): LiveData<ResponseStdModel>

    suspend fun updatePendingCode(codeId: Int): LiveData<ResponseStdModel>
}
