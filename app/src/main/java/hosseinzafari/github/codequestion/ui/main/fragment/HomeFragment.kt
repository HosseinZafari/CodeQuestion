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
import hosseinzafari.github.codequestion.adapter.BestUserRVAdapter
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.ui.viewmodel.HomeViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment


class HomeFragment : GFragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val bestUserAdapter = BestUserRVAdapter(){
        Toast.makeText(activity, "clicked id is $it", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // setup recyclerview
        val bestUserRecyclerView = view.findViewById<RecyclerView>(R.id.rv_home_bestuser)
        bestUserRecyclerView.layoutManager = LinearLayoutManager(activity , LinearLayoutManager.HORIZONTAL , true)
        bestUserRecyclerView.adapter = bestUserAdapter

        homeViewModel.getBestUser().observe(viewLifecycleOwner , Observer {
            when(it.status){
                Status.ERROR -> log("Error ${it.message}")
                Status.LOADING -> log("Loading ${it.message}")
                Status.SUCCEESS -> {
                    log("Success ${it.data}")
                    bestUserAdapter.updateData(it.data)
                }
            }
        })
    }
}
