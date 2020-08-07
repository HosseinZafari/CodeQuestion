package hosseinzafari.github.codequestion.ui.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import hosseinzafari.github.codequestion.R
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

    }


    private suspend fun isLogin() {
        questionViewModel.getToken().observe(viewLifecycleOwner, Observer {
            if (it == null) { // invalid - block user to question
                uiUtil.replaceFragment(FactoryFragment.LOGIN_FRAGMENT)
            }
        })
    }
}
