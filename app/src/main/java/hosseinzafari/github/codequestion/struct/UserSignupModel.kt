package hosseinzafari.github.codequestion.struct

/*

@created in 03/08/2020 - 09:53 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

data class UserSignupModel (
    val name: String ,
    val family: String ,
    val phoneNumber: String ,
    val gender: Byte ,
    val password: String ,
    val email: String
)