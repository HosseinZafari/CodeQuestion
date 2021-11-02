package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.AdminAnswerRVAdapter
import hosseinzafari.github.codequestion.adapter.AnswerRVAdapter
import hosseinzafari.github.codequestion.struct.AnswerModel
import hosseinzafari.github.codequestion.ui.dialogs.AdminAnswerDialog
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.helper.toast
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.QuestionViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 13/12/2020 - 9:34 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AdminAnswerFragment : GFragment() {
	
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
	= inflater.inflate(R.layout.fragment_admin_answer , container , false)
	
	var isShowAnimation = true
	private lateinit var txt_state_question: TextView
	private lateinit var linearLayout_empty: LinearLayoutCompat
	private lateinit var rv_answer: RecyclerView
	private val questionViewModel: QuestionViewModel by viewModels()
	private val answerAdapter = AdminAnswerRVAdapter(::onWrongItemClickHandle)
	
	private fun onWrongItemClickHandle(answer: AnswerModel , type: Int) {
		if(type == AdminAnswerRVAdapter.TYPE_WRONG) {
			questionViewModel.returnedQuestion(if(answer.returned!!) 0 else 1 , answer.questionId)
		} else {
			val dialog = AdminAnswerDialog(answer)
			dialog.show(childFragmentManager , null)
		}
	}
	
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		setupViews(view)
		fetchData()
	}
	
	private fun setupViews(view: View){
		txt_state_question   = view.findViewById(R.id.txt_admin_state_question)
		linearLayout_empty   = view.findViewById(R.id.linearLayout_admin_empty)
		rv_answer            = view.findViewById(R.id.rv_admin_answer)
		rv_answer.adapter    = answerAdapter
	}
	
	
	private fun fetchData() {
		questionViewModel.answers().observe(viewLifecycleOwner) {
			when(it.status) {
				Status.ERROR -> { log("Error in Getting Answers ..." + it.data?.msg) }
				Status.LOADING -> { log("Loading in Getting Answers ..." + it.data?.msg) }
				Status.SUCCEESS -> {
					if (it.data == null || it.data.answers == null || it.data.code >= 300) {
						toast("  مشکلی در دریافت سوالات وجود دارد" + it.data)
						return@observe
					}
					
					// set data to adapter and show Recyclerview
					if(it.data.answers.size > 0) {
						rv_answer.visibility = View.VISIBLE
						linearLayout_empty.visibility = View.GONE
					}
					rv_answer.anim(Techniques.SlideInDown)
					answerAdapter.data = it.data.answers
				}
			}
		}
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		
		if(isShowAnimation)
			uiUtil.getLayoutRootFragment().anim(Techniques.SlideInRight)
		else
			isShowAnimation = true
	}
	
}