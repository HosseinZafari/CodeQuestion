package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.RulesRVAdapter
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.QuestionViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 11/08/2020 - 02:39 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class RulesFragment : GFragment() {

    private val questionViewModel : QuestionViewModel by viewModels()
    private val adapter = RulesRVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rules , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv_rules = view.findViewById<RecyclerView>(R.id.rv_rules)
        rv_rules.adapter = adapter

        questionViewModel.getRules().observe(viewLifecycleOwner , Observer {
            when(it.status) {
                Status.ERROR -> { log("Error in Getting Rules ..." + it.data?.msg) }
                Status.LOADING -> { log("Loading in Getting Rules ..." + it.data?.msg) }
                Status.SUCCEESS -> {
                    log("Successfully question .. " + it.data)
                    adapter.updateData(it.data?.rules)
                }
            }
        })

    }
}