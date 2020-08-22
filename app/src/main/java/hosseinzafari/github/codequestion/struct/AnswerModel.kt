package hosseinzafari.github.codequestion.struct


data class AnswerModel(
    val isAdmin: Int ,
    val title: String,
    val text: String,
    val course: String,
    val date: String,
    val type: String ,
)