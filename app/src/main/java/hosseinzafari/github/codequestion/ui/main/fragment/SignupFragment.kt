package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.core.util.PatternsCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.UserSignupModel
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.QuestionViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 03/08/2020 - 08:56 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class SignupFragment : GFragment() {

    private val questionViewModel: QuestionViewModel by viewModels()
    
    private lateinit var  edt_layout_name: TextInputLayout
    private lateinit var  edt_layout_family: TextInputLayout 
    private lateinit var  edt_layout_email : TextInputLayout
    private lateinit var  edt_layout_number : TextInputLayout
    private lateinit var  edt_layout_pass : TextInputLayout
    private lateinit var  edt_layout_repass : TextInputLayout
    private lateinit var  edt_gender : RadioGroup
    private lateinit var  btn_signup_create: Button
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_singup , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
    }
    
    private fun setupViews(view: View){
        edt_layout_name = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_name)
        edt_layout_family = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_family)
        edt_layout_email = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_email)
        edt_layout_number = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_numberphone)
        edt_layout_pass = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_pass)
        edt_layout_repass = view.findViewById<TextInputLayout>(R.id.edt_singup_layout_repass)
        edt_gender = view.findViewById<RadioGroup>(R.id.radio_group_gender_signup)
        btn_signup_create = view.findViewById<Button>(R.id.btn_singup_create)
        

        // set listener
        edt_layout_name.editText?.doOnTextChanged { text, _, _, _ ->
            clearError(edt_layout_name)
        }
        edt_layout_family.editText?.doOnTextChanged { text, _, _, _ ->
            clearError(edt_layout_family)
        }
        edt_layout_email.editText?.doOnTextChanged {text, _, _, _ ->
            clearError(edt_layout_email)
        }
        edt_layout_number.editText?.doOnTextChanged {text, _, _, _ ->
            clearError(edt_layout_number)
        }
        edt_layout_pass.editText?.doOnTextChanged {text, _, _, _ ->
            clearError(edt_layout_pass)
        }
        edt_layout_repass.editText?.doOnTextChanged {text, _, _, _ ->
            clearError(edt_layout_repass)
        }

        btn_signup_create.setOnClickListener(::createAccount)
    }

    private fun clearError(edt: TextInputLayout){
        if(edt.error != null)
            edt.error = null
    }

    private fun createAccount(view: View){
        val firstName = edt_layout_name.editText?.text.toString()
        val lastName = edt_layout_family.editText?.text.toString()
        val email  = edt_layout_email.editText?.text.toString()
        val number = edt_layout_number.editText?.text.toString()
        val pass   = edt_layout_pass.editText?.text.toString()
        val repass = edt_layout_repass.editText?.text.toString()
        
        var isInvalid = false
        
        // check empty value 
        if(firstName.isBlank()){
            edt_layout_name.error = "لطفا تمامی فیلد ها را پر کنید."       
            isInvalid = true
        }
        
        if(lastName.isBlank()){
            edt_layout_family.error = "لطفا تمامی فیلد ها را پر کنید."       
            isInvalid = true
        }
        
        if(email.isBlank()){
            edt_layout_email.error = "لطفا تمامی فیلد ها را پر کنید."       
            isInvalid = true
        }
        
        if(number.isBlank()){
            edt_layout_number.error = "لطفا تمامی فیلد ها را پر کنید."       
            isInvalid = true
        }
        
        if(pass.isBlank()){
            edt_layout_pass.error = "لطفا تمامی فیلد ها را پر کنید."       
            isInvalid = true
        }
        
        if(repass.isBlank()){
            edt_layout_repass.error = "لطفا تمامی فیلد ها را پر کنید."       
            isInvalid = true
        }

        if(isInvalid)
            return

        // check format value 
        if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){ 
            edt_layout_email.error = "لطفا ایمیل صحیحی را وارد نمایید."       
            isInvalid = true
        }
        
        if(number.length != 11){
            edt_layout_number.error = "شماره همراه شما نادرست است."    
            isInvalid = true
        }
        
        if(!pass.isBlank() && pass.length < 4){
            edt_layout_pass.error = "رمز عبور باید بیشتر از 4 حرف باشد."
            isInvalid = true
        }
        
        if(!pass.equals(repass)){
            edt_layout_pass.error = "رمز و تکرار آن یکسان نیست."       
            edt_layout_repass.error = "رمز و تکرار آن یکسان نیست."       
            isInvalid = true
        }
        
        // blocked if is invalid
        if(isInvalid)
            return 
        
        val gender: Byte = if(edt_gender.checkedRadioButtonId == R.id.radio_btn_male) 0 else 1
        
        questionViewModel.signupUser(UserSignupModel(firstName , lastName , number , gender , pass , email)).observe(viewLifecycleOwner , Observer { 
            when(it.status){
                Status.ERROR -> log("Signup Error ${it.message}")
                Status.LOADING -> log("Signup Loading ${it.message}")
                Status.SUCCEESS -> {
                    log("Signup Success ${it.data}")
                    if(it.data == null || it.data.auth == null){
                        return@Observer
                    }

                    // save token
                    questionViewModel.setToken(it.data.auth)
                }
            }
        })
        
    }


    
}