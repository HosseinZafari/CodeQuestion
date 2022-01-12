package hosseinzafari.github.codequestion.adapter

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.framework.extensions.inflate

/*
    @created in 02/12/2020 - 4:27 PM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

class AllCourseAdapter(
        private var data: List<CourseModel> = listOf(),
        private val onClickEvent: CourseModel.() -> Unit
) : RecyclerView.Adapter<AllCourseAdapter.AllCourseViewHolder>() {

    fun  updateData(data: List<CourseModel>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AllCourseViewHolder(parent.inflate(R.layout.item_rv_course_list))

    override fun onBindViewHolder(holder: AllCourseViewHolder, position: Int) {
        val courseModel = data[position]
        val imgUrl = Uri.parse(courseModel.image.toString())
        holder.img_item.setImageRequest(ImageRequest.fromUri(imgUrl))

        holder.txt_title.text = courseModel.title

        holder.rootView.setOnClickListener { onClickEvent(courseModel) }
    }

    override fun getItemCount() = data.size


    class AllCourseViewHolder(
            val rootView: View ,
            val img_item: SimpleDraweeView = rootView.findViewById(R.id.img_item_course) ,
            val txt_title: TextView = rootView.findViewById(R.id.txt_title_item_course) ,
    ) : RecyclerView.ViewHolder(rootView) {

    }

}