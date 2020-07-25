package hosseinzafari.github.codequestion.ui.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/*
    @created in 25/07/2020 - 02:11 PM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

inline fun <reified T> getViewModel(crossinline blockGenerator: () -> T) = object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return blockGenerator() as T
    }
}