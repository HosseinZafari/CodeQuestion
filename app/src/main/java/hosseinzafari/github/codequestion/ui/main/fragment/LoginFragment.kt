package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.util.UiUtil
import hosseinzafari.github.codequestion.ui.viewmodel.QuestionViewModel
import hosseinzafari.github.framework.core.app.G
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 03/08/2020 - 08:56 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class LoginFragment : GFragment(), View.OnClickListener{

    private val questionViewModel : QuestionViewModel by viewModels()
    private lateinit var  edt_login_email : TextInputLayout
    private lateinit var  edt_login_password : TextInputLayout
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
        
    }
    
    private fun setupViews(view: View){
        val img_back_login    = view.findViewById<ImageButton>(R.id.img_back_login)
        val btn_gosignup     = view.findViewById<MaterialButton>(R.id.btn_login_gosingup)
        val btn_login_enter       = view.findViewById<Button>(R.id.btn_login_enter)
        
        edt_login_email = view.findViewById(R.id.edt_login_email)
        edt_login_password = view.findViewById(R.id.edt_login_layout_pass)

        // listener for views
        edt_login_email.editText?.doOnTextChanged { text, _, _, _ ->
            if(edt_login_email.error != null)
                edt_login_email.error = null
        }

        edt_login_password.editText?.doOnTextChanged { text, _, _, _ ->
            if(edt_login_password.error != null)
                edt_login_password.error = null
        }

        img_back_login.setOnClickListener { activity?.onBackPressed() }
        btn_gosignup.setOnClickListener { UiUtil(requireActivity()).replaceFragmentWithBack(FactoryFragment.SIGNUP_FRAGMENT) }
        btn_login_enter.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // Login Clicked Button
        val email = edt_login_email.editText?.text.toString()
        val password = edt_login_password.editText?.text.toString()
        var isHaveError = false

        if (email.isEmpty()){
            edt_login_email.error =  "لطفا تمامی فیلد ها را وارد کنید."
            isHaveError = true
        }

        if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            edt_login_email.error =  "ایمیل شما نا معتبر است."
            isHaveError = true
        }

        if(password.isEmpty()){
            edt_login_password.error = "لطفا تمامی فیلد ها را وارد کنید.";
            isHaveError = true
        }

        // blocked onClick
        if(isHaveError)
            return


        questionViewModel.loginUser(email , password).observe(viewLifecycleOwner , Observer {
            when(it.status) {
                Status.ERROR -> log("Login Error ${it.message}")
                Status.LOADING -> log("Login Loading ${it.message}")
                Status.SUCCEESS -> {
                    log("Login Success ${it.data}")
                    if(it.data == null){
                        log("Login Success is Null ")
                        return@Observer
                    }
                    
                    // Handle Errors and Show for User
                    if(it.data.code == 402){
                        setError(it.data.msg , edt_login_email)
                        setError(it.data.msg , edt_login_password)
                    } else if(it.data.code == 400){
                        setError(it.data.msg , edt_login_email)
                    } else if(it.data.code == 404){
                        setError(it.data.msg , edt_login_email)
                    } else if(it.data.code == 401){
                        log("401 code")
                        setError(it.data.msg , edt_login_password)
                    } else if(it.data.code == 200) {
                        Toast.makeText(G.getContext(), "${it.data.user?.name} خوش آمدید.", Toast.LENGTH_SHORT).show()
                        log("succeess code 200 " + it.data.user)
                    }

                }
            }
        })

    }

    private fun setError(text: String? , editText: TextInputLayout){
        editText.error = text
    }
}
