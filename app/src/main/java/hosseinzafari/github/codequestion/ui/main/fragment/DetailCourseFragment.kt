package hosseinzafari.github.codequestion.ui.main.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.daimajia.androidanimations.library.Techniques
import com.facebook.drawee.view.SimpleDraweeView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.helper.toast
import hosseinzafari.github.codequestion.ui.viewmodel.CourseViewModel
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
        const val BOOKMARKED = 0
        const val NO_BOOKMARK = 1
    }

    private lateinit var courseModel: CourseModel
    private val courseViewModel: CourseViewModel by viewModels()

    private lateinit var btnBookmark: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseModel = arguments?.getParcelable(KEY_DETAIL_COURSE)!!
        return inflater.inflate(R.layout.fragment_detail_course , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        stateBookmarkCourse()
    }

    private fun stateBookmarkCourse(){
        courseViewModel.getBookmarkCourseById(courseModel.courseId!!.toInt()).observe(viewLifecycleOwner , {
            if(it == null) { // this not bookmarked
                btnBookmark.setImageResource(R.drawable.ic_empty_bookmark)
                btnBookmark.tag = NO_BOOKMARK
            } else { // this is bookmark
                btnBookmark.setImageResource(R.drawable.ic_fill_bookmark)
                btnBookmark.tag = BOOKMARKED
            }
        })
    }

    private fun setupViews(view: View) {
        val txt_title  = view.findViewById<TextView>(R.id.txt_detailcourse_title)
        val txt_text   = view.findViewById<TextView>(R.id.txt_detailcourse_text)
        val txt_price  = view.findViewById<TextView>(R.id.txt_detailcourse_price)
        val txt_link   = view.findViewById<TextView>(R.id.txt_detailcourse_link)
        val txt_priority   = view.findViewById<TextView>(R.id.txt_detailcourse_priority)
        val imageDraweeView    = view.findViewById<SimpleDraweeView>(R.id.img_detailcourse)
        val img_share = view.findViewById<ImageButton>(R.id.img_share_course)
        val btnBack   = view.findViewById<ImageButton>(R.id.btn_back)
        btnBookmark   = view.findViewById<ImageButton>(R.id.img_detailcourse_bookmark)

        txt_title.text    = courseModel.title
        txt_text.text     = courseModel.text
        txt_price.text    = " قیمت این دوره ${courseModel.price} هزار تومان "
        txt_priority.text = " اولویت زمانی برای این دوره ${courseModel.priority}"
        imageDraweeView.setImageURI(Uri.parse(courseModel.image))


        txt_link.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW , Uri.parse(courseModel?.link))) }
        img_share.setOnClickListener { shareCourse() }
        btnBack.setOnClickListener  { requireActivity().onBackPressed() }
        btnBookmark.setOnClickListener { bookmarkOnOrOff() }
    }

    private fun bookmarkOnOrOff() {
        log("bookmark clicked")
        if(btnBookmark.tag == BOOKMARKED){ // must delete
            deleteBookmark()
        } else { // must add
            addBookmark()
        }
    }

    private fun shareCourse(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.setTypeAndNormalize("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT , courseModel.title + "\n \n" + courseModel.link)
        startActivity(Intent.createChooser(intent , " انتشار : ${courseModel.title}"))
    }

    private fun deleteBookmark(){
        val bookmarkEntity = convertCourseModel2Entity(courseModel)
        courseViewModel.deleteBookmarkCourse(bookmarkEntity)
        toast("آموزش از لیست نشان شده ها حذف شد.")
    }

    private fun addBookmark() {
        val bookmarkEntity  = convertCourseModel2Entity(courseModel)
        courseViewModel.addBookmarkCourse(bookmarkEntity )
        toast("این آموزش به نشان شده ها افزوده شد")
    }


    private fun convertCourseModel2Entity(courseModel: CourseModel) =
        with(courseModel) { BookmarkCourseEntity(courseId!!   , title   , text   , link   , image   , priority   , price   , star   , color) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getLayoutRootFragment().anim(Techniques.FadeIn , 350)
    }

}