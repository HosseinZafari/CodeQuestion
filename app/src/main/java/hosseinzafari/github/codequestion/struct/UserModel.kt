package hosseinzafari.github.codequestion.ui.struct


data class UserModel(
    val userId: String? = null,  // 2
    val name: String? = null,   // حسین
    val family: String? = null, // ظفری
    val phoneNumber: String? = null,
    val email: String? = null,
    val role: String? = null,
    val gender: Byte? = 0,  // 0 or 1 == male or female
    val image: String? = null,  // https://ware.uncox.com/asset/profile/male/15.jpg
    val point: String? = null ,  // 40
    val token: String? = null
)