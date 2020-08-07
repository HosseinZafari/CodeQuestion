package hosseinzafari.github.codequestion.local

import android.content.Context
import hosseinzafari.github.framework.core.app.G

/*

@created in 07/08/2020 - 10:10 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

object SharedPref {

    private val sharedName = G.getContext().packageName + ".shared"
    private val shared_pref = G.getContext().getSharedPreferences(sharedName , Context.MODE_PRIVATE)
    private val editor = shared_pref.edit()


    enum class Key(value: String) {
        au("au")
    }

    fun setToken(token: String){
        editor.putString(Key.au.name , token)
        editor.apply()
    }


    fun getToken() = shared_pref.getString(Key.au.name , null)
}