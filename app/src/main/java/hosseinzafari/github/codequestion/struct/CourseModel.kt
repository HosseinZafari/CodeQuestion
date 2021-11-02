package hosseinzafari.github.codequestion.struct

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseModel(
    val  courseId: String?,
    val text: String?,
    val title: String?,
    val image: String?,
    val link: String?,
    val color: String?,
    val price: String?,
    val priority: String?,
    val star: String?
) : Parcelable