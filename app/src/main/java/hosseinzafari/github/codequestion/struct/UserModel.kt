package hosseinzafari.github.codequestion.ui.struct


data class UserModel(
    val family: String, // ظفری
    val image: String?,  // https://ware.uncox.com/asset/profile/male/15.jpg
    val name: String,   // حسین
    val gender: Byte,  // 0 or 1 == male or female
    val point: String,  // 40
    val userId: String  // 2
)