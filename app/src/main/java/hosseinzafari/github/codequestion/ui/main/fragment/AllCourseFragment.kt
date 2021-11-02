package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.AllCourseAdapter
import hosseinzafari.github.codequestion.struct.CourseModel
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.helper.toast
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.CourseViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment
import hosseinzafari.github.framework.extensions.inflate

/*

@created in 02/12/2020 - 4:08 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AllCourseFragment : GFragment() {
    private lateinit var framelayout_all_course: FrameLayout
    private lateinit var recyclerViewCourses: RecyclerView

    private val allCourseAdapter = AllCourseAdapter(onClickEvent = ::onClickEvent)
    private val courseViewModel: CourseViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = container?.inflate(R.layout.fragment_all_course)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getLayoutRootFragment().anim(Techniques.SlideInRight)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        populateAllCourse()
    }

    private fun setupViews(view: View){
        recyclerViewCourses = view.findViewById<RecyclerView>(R.id.rv_allcourses)
        framelayout_all_course = view.findViewById<FrameLayout>(R.id.framelayout_all_course)
        recyclerViewCourses.adapter = allCourseAdapter
    }

    private fun onClickEvent(courseModel: CourseModel) {
        val argument = Bundle().apply {
            putParcelable(DetailCourseFragment.KEY_DETAIL_COURSE , CourseModel(
                    courseModel.courseId ,
                    courseModel.text ,
                    courseModel.title ,
                    courseModel.image ,
                    courseModel.link ,
                    courseModel.color ,
                    courseModel.price ,
                    courseModel.priority ,
                    courseModel.star
            ))
        }

        ContainerFragment.addFragmentWithBack(
                activity = requireActivity() ,
                targetFragment = FactoryFragment.DETAIL_COURSE_FRAGMENT ,
                tag = "AllCourseToDetailCourse" ,
                argument = argument
        )
    }

    private fun hideRecylcerView(){
        framelayout_all_course.visibility = View.VISIBLE
        recyclerViewCourses.visibility = View.GONE
    }

    private fun showRecylcerView(){
        framelayout_all_course.visibility = View.GONE
        recyclerViewCourses.visibility = View.VISIBLE
    }

    private fun populateAllCourse(){
        courseViewModel.getAllCourse().observe(viewLifecycleOwner , {
            when(it.status) {
                Status.LOADING -> {
                    hideRecylcerView()
                    log(it.message!!)
                }

                Status.ERROR -> {
                    hideRecylcerView()
                    toast(it.message!!)
                }

                Status.SUCCEESS -> {
                    showRecylcerView()
                    it.data ?: return@observe
                    allCourseAdapter.updateData(it.data)
                }
            }
        })
    }

}