package hosseinzafari.github.codequestion.helper


/*

@created in 13/09/2020 - 12:25 PM
@project Code Question
@author  Hossein Zafari
@email   hosseinzafari2000@gmail.com
*/

abstract class SingletonHelper<A, B>() {

    @Volatile
    private var INSTANCE: B? = null

    abstract protected fun creator(arg: A): B

    fun getInstance(arg: A): B {
        val temporaryInstance1 = INSTANCE
        if(temporaryInstance1 != null) {
            return temporaryInstance1
        }

        return synchronized(this) {
            val temporaryInstance2 = INSTANCE
            if(temporaryInstance2 != null){
                INSTANCE!!
            } else {
                val createdInstance = creator(arg)
                INSTANCE = createdInstance
                INSTANCE!!
            }
        }
    }
}