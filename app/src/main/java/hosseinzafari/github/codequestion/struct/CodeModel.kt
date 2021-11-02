package hosseinzafari.github.codequestion.struct

import android.os.Parcel
import android.os.Parcelable


data class CodeModel(
    val codeId: String?,
    var codePoint: String?,
    val date: String?,
    val family: String?,
    val image: String?,
    val name: String?,
    val source: String?,
    val text: String?,
    val title: String?,
    val userPoint: String? ,
    val gender: String? ,
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
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(codeId)
        parcel.writeString(codePoint)
        parcel.writeString(date)
        parcel.writeString(family)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(source)
        parcel.writeString(text)
        parcel.writeString(title)
        parcel.writeString(userPoint)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CodeModel> {
        override fun createFromParcel(parcel: Parcel): CodeModel {
            return CodeModel(parcel)
        }

        override fun newArray(size: Int): Array<CodeModel?> {
            return arrayOfNulls(size)
        }
    }
}