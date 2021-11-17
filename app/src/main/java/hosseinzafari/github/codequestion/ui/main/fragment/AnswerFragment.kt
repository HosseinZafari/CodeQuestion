package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.AnswerRVAdapter
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.helper.toast
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.QuestionViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 19/08/2020 - 01:34 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AnswerFragment : GFragment() {


    var isShowAnimation = true
    private lateinit var fab_new_question: FloatingActionButton
    private lateinit var btn_new_question: Button
    private lateinit var linearLayout_empty: LinearLayoutCompat
    private lateinit var rv_answer: RecyclerView
    private val questionViewModel: QuestionViewModel by viewModels()
    private val answerAdapter = AnswerRVAdapter()
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_answer , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentPage = 1
        setupViews(view)
        fetchData()
    }

    private fun setupViews(view: View){
        fab_new_question   = view.findViewById(R.id.fab_new_question_fragment_answer)
        btn_new_question   = view.findViewById(R.id.btn_new_question)
        linearLayout_empty = view.findViewById(R.id.linearLayout_empty)
        rv_answer = view.findViewById(R.id.rv_answer)
        rv_answer.adapter = answerAdapter
        rv_answer.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!recyclerView.canScrollVertically(1)) {
                    fetchData(false) // reduce performance because call multiple request when user keep scroll to bottom
                }
            }
        })
        fab_new_question.setOnClickListener(::newQuestion)
        btn_new_question.setOnClickListener(::newQuestion)

    }


    private fun fetchData(isFirstPage: Boolean = true ) {
        questionViewModel.answers(currentPage).observe(viewLifecycleOwner) {
            log("fetch : "  + it)
            when(it.status) {
                Status.ERROR -> { log("Error in Getting Answers ..." + it.data?.msg) }
                Status.LOADING -> { log("Loading in Getting Answers ..." + it.data?.msg) }
                Status.SUCCEESS -> {
                    if (it.data == null || it.data.answers == null || it.data.code >= 300) {
                        toast("مشکلی در دریافت سوالات وجود دارد")
                        return@observe
                    }

                    if(it.data.answers.size == 0) {
                        toast("سوال دیگری وجود ندارد" )
                        return@observe
                    }

                    // set data to adapter and show Recyclerview
                    if(it.data.answers.size > 0 && isFirstPage) {
                        rv_answer.visibility = View.VISIBLE
                        linearLayout_empty.visibility = View.GONE
                    }

                    if (isFirstPage) {
                        rv_answer.anim(Techniques.SlideInDown)
                        answerAdapter.data = it.data.answers.toMutableList()
                    } else {
                        answerAdapter.putNewData(it.data.answers)
                    }

                    currentPage++
                }
            }

        }
    }

    private fun newQuestion(v: View){
        ContainerFragment.replaceFragmentWithBack(requireActivity() , FactoryFragment.ASK_FRAGMENT , tag = "QuestionInAnswerFragment")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(isShowAnimation)
            uiUtil.getLayoutRootFragment().anim(Techniques.SlideInRight)
        else
            isShowAnimation = true
    }
}
