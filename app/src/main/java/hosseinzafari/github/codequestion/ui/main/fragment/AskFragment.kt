package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 12/08/2020 - 04:41 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AskFragment : GFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ask , container , false)
    }


}