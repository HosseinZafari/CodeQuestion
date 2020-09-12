package hosseinzafari.github.codequestion.ui.main.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.facebook.drawee.view.SimpleDraweeView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 11/09/2020 - 10:46 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class DetailCourseFragment : GFragment() {

    companion object {
        const val KEY_DETAIL_COURSE = "KEY_DETAIL_COUSRE"
    }

    private var courseModel: CourseModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseModel = arguments?.getParcelable(KEY_DETAIL_COURSE)
        return inflater.inflate(R.layout.fragment_detail_course , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
    }

    private fun setupViews(view: View) {
        val txt_title  = view.findViewById<TextView>(R.id.txt_detailcourse_title)
        val txt_text   = view.findViewById<TextView>(R.id.txt_detailcourse_text)
        val txt_price  = view.findViewById<TextView>(R.id.txt_detailcourse_price)
        val txt_link   = view.findViewById<TextView>(R.id.txt_detailcourse_link)
        val txt_priority   = view.findViewById<TextView>(R.id.txt_detailcourse_priority)
        val imageDraweeView    = view.findViewById<SimpleDraweeView>(R.id.img_detailcourse)
        val btnBack   = view.findViewById<ImageButton>(R.id.btn_back)
        val btnBookmark   = view.findViewById<ImageButton>(R.id.img_detailcourse_bookmark)

        txt_title.text    = courseModel?.title
        txt_text.text     = courseModel?.text
        txt_price.text    = " قیمت این دوره ${courseModel?.price} هزار تومان "
        txt_priority.text = " اولویت زمانی برای این دوره ${courseModel?.priority}"
        imageDraweeView.setImageURI(Uri.parse(courseModel?.image))


        txt_link.setOnClickListener {
            // Open Browser For This Link
            startActivity(Intent(Intent.ACTION_VIEW , Uri.parse(courseModel?.link)))
        }

        btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        btnBookmark.setOnClickListener {
            log("Bookmark Clicked !!!")
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getContainerFragment().anim(Techniques.FadeIn)
    }

}