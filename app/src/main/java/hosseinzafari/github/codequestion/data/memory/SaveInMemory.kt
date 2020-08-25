package hosseinzafari.github.codequestion.data.memory

import hosseinzafari.github.codequestion.R

/*

@created in 17/08/2020 - 09:37 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

object SaveInMemory {

    var token = String()

    object Resource {

        val genderResource: Map<String , Int> by lazy {
            mapOf(
                "0" to R.drawable.user_man,
                "1" to R.drawable.user_famale,
            )
        }
    }
}