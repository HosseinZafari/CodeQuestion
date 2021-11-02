package hosseinzafari.github.codequestion.adapter

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity
import hosseinzafari.github.framework.extensions.inflate

/*

@created in 30/11/2020 - 1:41 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class BookmarkRvAdapter(val onClickEvent: BookmarkCourseEntity.()-> Unit) : RecyclerView.Adapter<BookmarkRvAdapter.BookmarkViewHolder>() {

    private var bookmarkCourseList: List<BookmarkCourseEntity> = listOf()

    fun updateData(data: List<BookmarkCourseEntity>){
        bookmarkCourseList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookmarkViewHolder(parent.inflate(R.layout.item_rv_course_list))

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmarkCourse = bookmarkCourseList[position]
        holder.bind(bookmarkCourse)
        holder.root.setOnClickListener { onClickEvent(bookmarkCourse) }
    }

    override fun getItemCount() = bookmarkCourseList.size

    class BookmarkViewHolder(
            val root: View,
            val txt_title_item_bookmark: TextView = root.findViewById(R.id.txt_title_item_course),
            val img_item_bookmark: SimpleDraweeView = root.findViewById(R.id.img_item_course)
    ) : RecyclerView.ViewHolder(root) {

        fun bind(bookmarkCourse: BookmarkCourseEntity){
            txt_title_item_bookmark.text = bookmarkCourse.title
            img_item_bookmark.setImageURI(Uri.parse(bookmarkCourse.image))
        }
    }
}