package hosseinzafari.github.codequestion.ui.util

import androidx.fragment.app.FragmentActivity
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment

/*

@created in 03/08/2020 - 10:33 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class UiUtil(val activity: FragmentActivity) {


    fun getFragment(byte: Byte) = FactoryFragment.getFragment(byte)

    fun addFragment(targetFragment: Byte , containerLayout: Int = R.id.framelayout , tag: String? = null) {
        activity.supportFragmentManager.beginTransaction().add(containerLayout , getFragment(targetFragment) , tag).commit()
    }

    fun replaceFragment(targetFragment: Byte, containerLayout: Int = R.id.framelayout, tag: String? = null) {
        activity.supportFragmentManager.beginTransaction().replace(containerLayout , getFragment(targetFragment) , tag).commit()
    }

    fun replaceFragmentWithBack(targetFragment: Byte, containerLayout: Int = R.id.framelayout, tag: String? = null) {
        activity.supportFragmentManager.beginTransaction().addToBackStack(tag).replace(containerLayout , getFragment(targetFragment) , tag).commit()
    }


}