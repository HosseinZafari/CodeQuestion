package hosseinzafari.github.codequestion.ui.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.daimajia.androidanimations.library.Techniques
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.main.fragment.ContainerFragment
import hosseinzafari.github.codequestion.ui.viewmodel.QuestionViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment


class QuestionFragment : GFragment() {

    private val questionViewModel: QuestionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var isAdmin = false
        
        questionViewModel.getRole().observe(viewLifecycleOwner , {
            if(it.equals("admin" , true)){
                isAdmin = true
            }
        })
        
        val fab_new_question    = view.findViewById<FloatingActionButton>(R.id.fab_new_question)
        val cv_show_rules       = view.findViewById<MaterialCardView>(R.id.cv_show_rules)
        val cv_list_rules       = view.findViewById<MaterialCardView>(R.id.cv_list_rules)
        val chk_agree_rules     = view.findViewById<MaterialCheckBox>(R.id.chk_agree_rules)

        fab_new_question.hide()

        cv_show_rules.setOnClickListener {
            ContainerFragment.replaceFragmentWithBack(requireActivity() , FactoryFragment.RULES_FRAGMENT , tag = "Rules")
        }

        cv_list_rules.setOnClickListener {
            if(isAdmin) { // Go to AdminFragmentQuestion
                ContainerFragment.replaceFragmentWithBack(requireActivity() , FactoryFragment.ADMIN_ANSWER_FRAGMENT , tag = "Answer")
            } else { // Go To UserFragmentQuestion
                ContainerFragment.replaceFragmentWithBack(requireActivity() , FactoryFragment.ANSWER_FRAGMENT , tag = "Answer")
            }
        }

        chk_agree_rules.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                chk_agree_rules.anim(Techniques.FadeOut)
                fab_new_question.anim(Techniques.SlideInRight)
                fab_new_question.show()
            } else {
                fab_new_question.hide()
            }
        }

        fab_new_question.setOnClickListener {
            ContainerFragment.replaceFragmentWithBack(requireActivity() , FactoryFragment.ASK_FRAGMENT , tag = "Ask")
        }
    }
    
    
}
