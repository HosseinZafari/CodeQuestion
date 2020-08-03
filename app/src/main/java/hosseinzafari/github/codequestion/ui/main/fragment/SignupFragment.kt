package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputLayout
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 03/08/2020 - 08:56 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class SignupFragment : GFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_singup , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val edt_layout_name = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_name)
        val edt_layout_family = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_family)
        val edt_layout_email = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_email)
        val edt_layout_numberphone = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_numberphone)
        val edt_layout_pass = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_pass)
        val edt_layout_repass = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_repass)

    }
}