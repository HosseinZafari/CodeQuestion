package hosseinzafari.github.codequestion.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.framework.core.app.G

/*

@created in 26/07/2020 - 06:52 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class CourseRVAdapter(val onItemClick:(CourseModel)-> Unit) : RecyclerView.Adapter<CourseRVAdapter.CourseViewHolder>() {

    var data : MutableList<CourseModel> = mutableListOf()
        private set

    fun updateData(data: List<CourseModel>?){
        data ?: return
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CourseViewHolder {
        val view = LayoutInflater.from(G.getContext()).inflate(R.layout.item_rv_course , parent , false)
        return CourseViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int)  =
        data[position].let { course ->
            holder.onBind(course)
            holder.root.setOnClickListener { onItemClick(course) }
        }

    class CourseViewHolder(
        val root : View ,
        val txt_title_course: TextView = root.findViewById(R.id.txt_title_course),
        val img_course: SimpleDraweeView = root.findViewById(R.id.img_course),
        val txt_price_course: TextView = root.findViewById(R.id.txt_price_course) ,
        val txt_prority_course: TextView = root.findViewById(R.id.txt_prority_course)
    ) : RecyclerView.ViewHolder(root) {

        fun onBind(courseModel: CourseModel){
            txt_title_course.text = courseModel.title
            txt_price_course.text = courseModel.price
            txt_prority_course.text = courseModel.priority
            img_course.setImageURI(Uri.parse(courseModel.image))
        }
    }
}
