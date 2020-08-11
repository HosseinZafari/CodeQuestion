package hosseinzafari.github.codequestion.ui.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.viewmodel.QuestionViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment
import kotlinx.coroutines.launch


class QuestionFragment : GFragment() {

    private val questionViewModel: QuestionViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Check Enter To Account
        lifecycleScope.launch {
            isLogin()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fab_new_question = view.findViewById<FloatingActionButton>(R.id.fab_new_question)
        val cv_show_rules       = view.findViewById<MaterialCardView>(R.id.cv_show_rules)
        val chk_agree_rules    = view.findViewById<MaterialCheckBox>(R.id.chk_agree_rules)

        fab_new_question.hide()

        cv_show_rules.setOnClickListener {
            uiUtil.replaceFragmentWithBack(FactoryFragment.RULES_FRAGMENT)
        }

        chk_agree_rules.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
               fab_new_question.show()
            } else {
                fab_new_question.hide()
            }
        }

        fab_new_question.setOnClickListener {
            log("Fab Clicked!!!") // TODO ADD A QUESTION
        }
    }


    private suspend fun isLogin() {
        questionViewModel.getToken().observe(viewLifecycleOwner, Observer {
            if (it == null) { // invalid - block user to question
                uiUtil.replaceFragment(FactoryFragment.LOGIN_FRAGMENT)
            }
        })
    }
}
