package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.daimajia.androidanimations.library.Techniques
import com.google.android.material.textfield.TextInputLayout
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.CodeViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 01/09/2020 - 01:16 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AddCodeFragment : GFragment() {

    private val codeViewModel: CodeViewModel by viewModels()

    private lateinit var edt_title : TextInputLayout
    private lateinit var edt_text : TextInputLayout
    private lateinit var edt_source : TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_add_code , container , false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
    }

    private fun setupViews(view: View) {
        edt_title = view.findViewById(R.id.edt_title_code)
        edt_text = view.findViewById(R.id.edt_text_code)
        edt_source = view.findViewById(R.id.edt_source_code)
        val btn_send = view.findViewById<Button>(R.id.btn_send_code)

        edt_title.editText?.doOnTextChanged { _, _, _, _ ->
            if(edt_title.error != null){
                edt_title.error = null
            }
        }

        edt_text.editText?.doOnTextChanged { _, _, _, _ ->
            if(edt_text.error != null){
                edt_text.error = null
            }
        }

        edt_source.editText?.doOnTextChanged { _, _, _, _ ->
            if(edt_source.error != null){
                edt_source.error = null
            }
        }

        btn_send.setOnClickListener(::sendCode)
    }


    private fun sendCode(view: View?) {
        val title = edt_title.editText?.text.toString()
        val text  = edt_text.editText?.text.toString()
        val source  = edt_source.editText?.text.toString()

        var isValid = true

        if(title.isBlank()){
            isValid = false
            edt_title.error = "تمامی ورودی ها را پر کنید"
        }

        if(text.isBlank()){
            isValid = false
            edt_text.error = "تمامی ورودی ها را پر کنید"
        }

        if(source.isBlank()){
            isValid = false
            edt_source.error = "تمامی ورودی ها را پر کنید"
        }

        if(!isValid)
            return

        // Call Api
        codeViewModel.addCode(title , text , source).observe(viewLifecycleOwner) {
            when(it.status){
                Status.LOADING -> log("Loading All Courses")
                Status.ERROR -> log("Error in All Courses")
                Status.SUCCEESS -> {
                    if (it.data == null || it.data.code > 300){
                        Toast.makeText(requireContext(), it.data?.msg, Toast.LENGTH_SHORT).show()
                        return@observe
                    }


                    Toast.makeText(requireContext(), "کد شما بعد از بررسی نمایش داده خواهد شد." , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getLayoutRootFragment().anim(Techniques.SlideInRight)
    }

}