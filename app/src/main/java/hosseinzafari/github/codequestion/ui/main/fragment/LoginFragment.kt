package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.util.UiUtil
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 03/08/2020 - 08:56 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class LoginFragment : GFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn_gosignup = view.findViewById<MaterialButton>(R.id.btn_login_gosingup)
        btn_gosignup.setOnClickListener {
            UiUtil(requireActivity()).replaceFragmentWithBack(FactoryFragment.SIGNUP_FRAGMENT)
        }
    }
}