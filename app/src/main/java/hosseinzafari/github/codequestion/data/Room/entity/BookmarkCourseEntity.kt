package hosseinzafari.github.codequestion.data.Room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/*
    @created in 13/09/2020 - 09:45 AM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

@Entity(tableName = "tb_bookmark_course")
data class BookmarkCourseEntity public constructor (
        @PrimaryKey val id: String,
        @ColumnInfo val title: String?,
        @ColumnInfo val text: String?,
        @ColumnInfo val link: String?,
        @ColumnInfo val image: String?,
        @ColumnInfo val priority: String?,
        @ColumnInfo val price: String?,
        @ColumnInfo val star: String?,
        @ColumnInfo val color: String?,
)