package hosseinzafari.github.codequestion.ui.main.fragment

import androidx.fragment.app.FragmentActivity
import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment

/*

@created in 08/09/2020 - 10:03 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

object FragmentHelper {

    enum class Destination(value: Byte) {
        QUESTION(0) ,
        CODE(1) ,
    }

    fun handleDestinationState(fragmentActivity: FragmentActivity) {
        when(SaveInMemory.destination) {
            Destination.QUESTION -> ContainerFragment.replaceFragment(fragmentActivity , FactoryFragment.QUESTION_FRAGMENT)
            Destination.CODE -> ContainerFragment.replaceFragment(fragmentActivity , FactoryFragment.CODE_FRAGMENT)
        }
    }


}