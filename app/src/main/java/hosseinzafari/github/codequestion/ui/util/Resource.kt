package hosseinzafari.github.codequestion.ui.ui.util

/*

@created in 25/07/2020 - 01:02 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String? 
) {
    
    companion object {
        fun <T> success(data: T) = Resource<T>(Status.SUCCEESS, data, "You Are Successfully")
        
        fun <T> loading() = Resource<T>(Status.LOADING, null, "Loading Pleace Wait ..")
        
        fun <T> error(message: String = "You Have An Error") = Resource<T>(Status.SUCCEESS, null, message)
    }
    
}
 