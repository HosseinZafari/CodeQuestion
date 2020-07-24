package hosseinzafari.github.codequestion.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.framework.core.ui.fragment.GFragment


class QuestionFragment : GFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

}
