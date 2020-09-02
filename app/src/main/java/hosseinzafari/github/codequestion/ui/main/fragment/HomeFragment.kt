package hosseinzafari.github.codequestion.ui.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.BestCodeRVAdapter
import hosseinzafari.github.codequestion.adapter.BestUserRVAdapter
import hosseinzafari.github.codequestion.adapter.CourseRVAdapter
import hosseinzafari.github.codequestion.ui.helper.log
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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

    private fun setupAdapters() {
        bestUserAdapter = BestUserRVAdapter { debugOnClick(it) }
        courseAdapter = CourseRVAdapter { debugOnClick(it) }
        bestCodeAdapter = BestCodeRVAdapter { debugOnClick(it) }
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
    }


    private fun debugOnClick(id: String) {
        Toast.makeText(activity, "clicked id is $id", Toast.LENGTH_SHORT).show()
    }
}
