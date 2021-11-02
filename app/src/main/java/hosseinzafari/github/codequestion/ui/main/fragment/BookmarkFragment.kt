package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.BookmarkRvAdapter
import hosseinzafari.github.codequestion.data.Room.entity.BookmarkCourseEntity
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.viewmodel.CourseViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 29/11/2020 - 6:26 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class BookmarkFragment : GFragment() {

    private val courseViewModel = CourseViewModel()
    private val adapterBookmark = BookmarkRvAdapter(::onClickEvent)
    private lateinit var  rv_bookmark: RecyclerView
    private lateinit var  linear_show_empty_list: LinearLayoutCompat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_bookmark , container , false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
        getBookmarkList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getLayoutRootFragment().anim(Techniques.SlideInRight , 200)
    }



    private fun setupViews(view: View){
        rv_bookmark = view.findViewById(R.id.rv_bookmark)
        linear_show_empty_list = view.findViewById(R.id.linear_show_empty_list)
        val img_back = view.findViewById<ImageButton>(R.id.img_back_bookmark)
        img_back.setOnClickListener { requireActivity().onBackPressed() }

        rv_bookmark.adapter = adapterBookmark
    }

    private fun getBookmarkList(){
        courseViewModel.getAllBookmarkCourse().observe(viewLifecycleOwner , {
            if(it != null) {
                adapterBookmark.updateData(it)
                showRecyclerView()
            } else { // no any bookmark in list
                hideRecyclerView()
            }
        })
    }


    private fun onClickEvent(courseBookmark: BookmarkCourseEntity){
        val argument = Bundle()
        val courseModel = CourseModel(
                courseBookmark.id ,
                courseBookmark.text ,
                courseBookmark.title ,
                courseBookmark.image ,
                courseBookmark.link ,
                courseBookmark.color ,
                courseBookmark.price ,
                courseBookmark.priority ,
                courseBookmark.star
        )

        argument.putParcelable(DetailCourseFragment.KEY_DETAIL_COURSE , courseModel)
        ContainerFragment.addFragmentWithBack(requireActivity() , FactoryFragment.DETAIL_COURSE_FRAGMENT , tag = "BookmarkCourseToDetailCourse" , argument = argument)
    }


    private fun hideRecyclerView(){
        linear_show_empty_list.visibility = View.VISIBLE
        rv_bookmark.visibility = View.GONE
    }

    private fun showRecyclerView(){
        linear_show_empty_list.visibility = View.GONE
        rv_bookmark.visibility = View.VISIBLE
    }
}