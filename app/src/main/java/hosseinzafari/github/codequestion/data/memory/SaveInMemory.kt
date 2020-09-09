package hosseinzafari.github.codequestion.data.memory

import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.main.fragment.FragmentHelper

/*

@created in 17/08/2020 - 09:37 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

object SaveInMemory {

    var token: String? = null
    var destination: FragmentHelper.Destination = FragmentHelper.Destination.QUESTION
    var userJsonInfo: String? = null

    object Resource {

        val genderResource: Map<String , Int> by lazy {
            mapOf(
                "0" to R.drawable.user_man,
                "1" to R.drawable.user_famale,
            )
        }
    }
}