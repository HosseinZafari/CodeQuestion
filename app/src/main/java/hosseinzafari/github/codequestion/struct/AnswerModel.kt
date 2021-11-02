package hosseinzafari.github.codequestion.struct


data class AnswerModel (
	val questionId: Int ,
    val isAdmin: Int ,
    val title: String,
    val text: String,
    val course: String,
    val date: String,
    val type: String ,
    val returned: Boolean? ,
    val answered: Boolean? ,
    val name: String? ,
    val family: String?
)