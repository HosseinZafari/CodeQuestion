package hosseinzafari.github.codequestion.struct

import android.os.Parcel
import android.os.Parcelable


data class CourseModel(
    val courseId: String?,
    val text: String?,
    val title: String?,
    val image: String?,
    val link: String?,
    val color: String?,
    val price: String?,
    val priority: String?,
    val star: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courseId)
        parcel.writeString(text)
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeString(link)
        parcel.writeString(color)
        parcel.writeString(price)
        parcel.writeString(priority)
        parcel.writeString(star)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseModel> {
        override fun createFromParcel(parcel: Parcel): CourseModel {
            return CourseModel(parcel)
        }

        override fun newArray(size: Int): Array<CourseModel?> {
            return arrayOfNulls(size)
        }
    }
}