package hosseinzafari.github.codequestion.ui.ui.main.fragment

import androidx.fragment.app.Fragment

/*

@created in 26/06/2020 - 10:09 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

object FactoryFragment {
    const val CART_FRAGMENT: Byte = 1
    const val HOME_FRAGMENT: Byte = 2
    const val PROFILE_FRAGMENT: Byte = 3
    const val CODE_FRAGMENT: Byte = 4
    const val QUESTION_FRAGMENT: Byte = 5

    fun getFragment(type: Byte): Fragment =
        when (type) {
            CART_FRAGMENT -> CartFragment()
            HOME_FRAGMENT -> HomeFragment()
            PROFILE_FRAGMENT -> ProfileFragment()
            CODE_FRAGMENT -> CodeFragment()
            QUESTION_FRAGMENT -> QuestionFragment()
            else -> throw IllegalArgumentException("Not Found Type Fragment For You")
        }

}