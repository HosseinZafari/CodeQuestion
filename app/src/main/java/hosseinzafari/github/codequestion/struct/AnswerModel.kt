package hosseinzafari.github.codequestion.struct


data class AnswerModel (
	var questionId: Int ,
    var toUser : Int ,
    var fromUser: Int ,
    var isAdmin: Int ,
    var title: String,
    var text: String,
    var course: String,
    var date: String,
    var type: Int ,
    var returned: Boolean? ,
    var answered: Boolean? ,
    var name: String? ,
    var family: String?
)