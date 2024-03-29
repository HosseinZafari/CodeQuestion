package hosseinzafari.github.codequestion.data.datasource.local.sharedpref

import android.content.Context
import hosseinzafari.github.framework.core.app.G

object SharedPref {

    private val sharedName = G.getContext().packageName + ".shared"
    private val shared_pref = G.getContext().getSharedPreferences(sharedName , Context.MODE_PRIVATE)
    private val editor = shared_pref.edit()


    enum class Key(value: String) {
        AU("AU") ,
        USER("USER") ,
        ROLE("ROLE") ,
    }

    fun setToken(token: String){
        editor.putString(Key.AU.name , token)
        editor.apply()
    }

    fun getToken() = shared_pref.getString(Key.AU.name , null)

    fun setUserJson(userJson: String){
        editor.putString(Key.USER.name , userJson)
        editor.apply()
    }
    
    fun getRole(): String {
        return shared_pref.getString(Key.ROLE.name , "user")!!
    }

    fun setRole(role: String) {
        editor.putString(Key.ROLE.name , role)
        editor.apply()
    }
    
    fun getUserJson() = shared_pref.getString(Key.USER.name , null)
}