package hosseinzafari.github.codequestion.struct

import hosseinzafari.github.codequestion.ui.struct.UserModel


data class ResponseStdModel(
    val code: Int,
    val msg: String,
    val status: String,
    val user: UserModel? = null,
    val auth: String? = null,
    val rules:   List<RulesModel>? = null,
    val courses: List<CourseModel>? = null ,
    val answers: List<AnswerModel>? = null ,
    val codes:   List<CodeModel>? = null ,

)