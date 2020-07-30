package hosseinzafari.github.codequestion.data.main

import androidx.lifecycle.LiveData
import hosseinzafari.github.codequestion.struct.CodeModel

interface CodeMain {

    suspend fun getBestCode() : LiveData<List<CodeModel>>

}
