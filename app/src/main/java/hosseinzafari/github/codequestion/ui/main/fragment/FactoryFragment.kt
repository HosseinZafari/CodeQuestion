package hosseinzafari.github.codequestion.ui.ui.main.fragment

import hosseinzafari.github.codequestion.ui.main.fragment.*
import hosseinzafari.github.framework.core.ui.fragment.GFragment

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
    const val LOGIN_FRAGMENT: Byte = 6
    const val SIGNUP_FRAGMENT: Byte = 7
    const val RULES_FRAGMENT: Byte = 8
    const val ASK_FRAGMENT: Byte = 9
    const val ANSWER_FRAGMENT: Byte = 10

    fun getFragment(type: Byte): GFragment =
        when (type) {
            CART_FRAGMENT -> CartFragment()
            HOME_FRAGMENT -> HomeFragment()
            PROFILE_FRAGMENT -> ProfileFragment()
            CODE_FRAGMENT -> CodeFragment()
            QUESTION_FRAGMENT -> QuestionFragment()
            LOGIN_FRAGMENT -> LoginFragment()
            SIGNUP_FRAGMENT -> SignupFragment()
            RULES_FRAGMENT -> RulesFragment()
            ASK_FRAGMENT -> AskFragment()
            ANSWER_FRAGMENT -> AnswerFragment()
            else -> throw IllegalArgumentException("Not Found Type Fragment For You")
        }

}