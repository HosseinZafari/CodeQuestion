package hosseinzafari.github.codequestion.ui.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.framework.core.ui.fragment.GFragment


class CodeFragment : GFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_code, container, false)
    }

}
