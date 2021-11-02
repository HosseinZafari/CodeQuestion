package hosseinzafari.github.codequestion.data.Room

import android.content.Context
import androidx.room.Room
import hosseinzafari.github.codequestion.helper.SingletonHelper
import hosseinzafari.github.framework.core.app.G

/*

@created in 13/09/2020 - 12:02 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

object AppDbBuilder : SingletonHelper<Context, AppDb>(){

    override fun creator(arg: Context) = Room.databaseBuilder(arg.applicationContext , AppDb::class.java , G.DB_NAME).build()

}