package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.QuestionViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment
import org.angmarch.views.NiceSpinner

/*

@created in 12/08/2020 - 04:41 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AskFragment : GFragment() {

    private val questionViewModel: QuestionViewModel by viewModels()
    private val spinnerData = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ask , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)

    }


    private fun setupViews(view: View){
        val edt_title = view.findViewById<TextInputLayout>(R.id.edt_title_ask)
        val edt_text = view.findViewById<TextInputLayout>(R.id.edt_text_ask)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radiogroup_ask)
        val spinner = view.findViewById<NiceSpinner>(R.id.spinner_ask)
        val btn_send = view.findViewById<Button>(R.id.btn_send_ask)

        questionViewModel.getAllCourses().observe(viewLifecycleOwner , Observer {
            when(it.status){
                Status.LOADING -> log("Loading All Courses")
                Status.ERROR -> log("Error in All Courses")
                Status.SUCCEESS -> {
                    if (it.data?.code!! > 300 || it.data.courses == null){
                        Toast.makeText(requireContext(), "مشکلی در برقرای ارتباط پیش آمده لطفا بعدا امتحان کنید.", Toast.LENGTH_SHORT).show()
                        return@Observer
                    }

                    it.data.courses.forEach { spinnerData.add(it.title) }
                    spinner.attachDataSource(spinnerData)
                }
            }
        })

        edt_title.editText?.doOnTextChanged { text, _, _, _ ->
            if(edt_title.error != null){
                edt_title.error = null
            }
        }

        edt_text.editText?.doOnTextChanged { text, _, _, _ ->
            if(edt_text.error != null){
                edt_text.error = null
            }
        }

        btn_send.setOnClickListener {
            val title = edt_title.editText?.text.toString()
            val text  = edt_text.editText?.text.toString()
            val spinnerSelected = spinner.selectedItem as String
            val checkdId = radioGroup.checkedRadioButtonId
            log("spinner selected is $spinnerSelected")

            var isValid = true
            if(title.isBlank()){
                isValid = false
                edt_title.error = "تمامی ورودی ها را پر کنید"
            }

            if(text.isBlank()){
                isValid = false
                edt_text.error = "تمامی ورودی ها را پر کنید"
            }

            if(!isValid)
                return@setOnClickListener

        }
    }

    private fun sendAsk(){

    }
}