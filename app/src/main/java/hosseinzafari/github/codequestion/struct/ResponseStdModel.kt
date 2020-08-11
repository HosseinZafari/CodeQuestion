package hosseinzafari.github.codequestion.struct

import hosseinzafari.github.codequestion.ui.struct.UserModel


data class ResponseStdModel(
    val code: Int,
    val msg: String,
    val status: String,
    val user: UserModel? = null,
    val auth: String? = null,
    val ruleModels: List<RulesModel>? = null
)