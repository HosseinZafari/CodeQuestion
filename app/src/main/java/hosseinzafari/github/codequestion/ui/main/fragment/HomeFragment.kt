package hosseinzafari.github.codequestion.ui.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.BestCodeRVAdapter
import hosseinzafari.github.codequestion.adapter.BestUserRVAdapter
import hosseinzafari.github.codequestion.adapter.CourseRVAdapter
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.main.fragment.ContainerFragment
import hosseinzafari.github.codequestion.ui.main.fragment.DetailCourseFragment
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.ui.viewmodel.HomeViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment


class HomeFragment : GFragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var bestUserAdapter: BestUserRVAdapter
    private lateinit var courseAdapter: CourseRVAdapter
    private lateinit var bestCodeAdapter: BestCodeRVAdapter

    private lateinit var rv_home_bestuser: RecyclerView;
    private lateinit var rv_home_bestcourse: RecyclerView;
    private lateinit var rv_home_bestcode: RecyclerView;

    var onClickShowCodes: ()-> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    private fun setupAdapters() {
        bestUserAdapter = BestUserRVAdapter { debugOnClick(it) }
        courseAdapter = CourseRVAdapter { courseModel->
            val arg = Bundle()
            arg.putParcelable(DetailCourseFragment.KEY_DETAIL_COURSE , courseModel)

            ContainerFragment.addFragmentWithBack(
                requireActivity() ,
                FactoryFragment.DETAIL_COURSE_FRAGMENT ,
                tag = "DetailCourse",
                argument = arg
            )
        }

        bestCodeAdapter = BestCodeRVAdapter {
            val arg = Bundle()
            arg.putParcelable(CodeFragment.KEY_CODE_MODEL , it)

            ContainerFragment.addFragmentWithBack(
                    requireActivity() ,
                    FactoryFragment.DETAIL_CODE_FRAGMENT ,
                    tag = "HomeFragmentToDetailFragment",
                    argument = arg
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupViews(view)

        homeViewModel.getBestUser().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.ERROR -> log("Error ${it.message}")
                Status.LOADING -> log("Loading ${it.message}")
                Status.SUCCEESS -> {
                    log("Success ${it.data}")
                    bestUserAdapter.updateData(it.data)
                }
            }
        })

        homeViewModel.getCourses().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.ERROR -> log("getCourses Error ${it.message}")
                Status.LOADING -> log("getCourses Loading ${it.message}")
                Status.SUCCEESS -> {
                    log("getCourses Success ${it.data}")
                    // adapter this here
                    courseAdapter.updateData(it.data?.courses)
                }
            }
        })

        homeViewModel.getCodes().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.ERROR -> log("getCodes Error")
                Status.LOADING -> log("getCodes is Loading")
                Status.SUCCEESS -> {
                    log("getCodes Success ${it.data}")
                    bestCodeAdapter.updateData(it.data)
                }
            }
        })
    }


    private fun setupViews(view: View) {
        // setup best user recyclerview
        rv_home_bestuser = view.findViewById(R.id.rv_home_bestuser)
        rv_home_bestuser.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
        rv_home_bestuser.adapter = bestUserAdapter

        // setup courses recyclerview
        rv_home_bestcourse = view.findViewById(R.id.rv_home_bestcourse)
        rv_home_bestcourse.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
        rv_home_bestcourse.adapter = courseAdapter

        // setup best codes recyclerview
        rv_home_bestcode = view.findViewById(R.id.rv_home_bestcode)
        rv_home_bestcode.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true)
        rv_home_bestcode.adapter = bestCodeAdapter

        val cardview_show_all_course = view.findViewById<CardView>(R.id.cardview_show_all_course)
        cardview_show_all_course.setOnClickListener {
            ContainerFragment.addFragmentWithBack(requireActivity() , FactoryFragment.ALL_COURSE_FRAGMNET , tag = "HomeFragmentToAllCourseFragment")
        }

        val txt_show_codes = view.findViewById<TextView>(R.id.txt_show_codes)
        txt_show_codes.setOnClickListener {
            ContainerFragment.addFragmentWithBack(requireActivity() , FactoryFragment.CODE_FRAGMENT , tag = "HomeFragmnetToCodes")
            onClickShowCodes()
        }
    }


    private fun debugOnClick(id: String?) {
        Toast.makeText(activity, "clicked id is $id", Toast.LENGTH_SHORT).show()
    }
}
