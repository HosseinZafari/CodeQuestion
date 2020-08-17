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
import com.daimajia.androidanimations.library.Techniques
import com.google.android.material.textfield.TextInputLayout
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.anim
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

class AskFragment : GFragment(){

    private val questionViewModel: QuestionViewModel by viewModels()
    private val spinnerData = mutableListOf<String>()
    private lateinit var edt_title: TextInputLayout
    private lateinit var edt_text: TextInputLayout
    private lateinit var radioGroup: RadioGroup
    private lateinit var spinner: NiceSpinner
    private lateinit var btn_send: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ask , container , false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
        fetchData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        uiUtil.getContainerFragment().anim(Techniques.SlideInRight , 200)
    }

    private fun setupViews(view: View){
       edt_title = view.findViewById(R.id.edt_title_ask)
       edt_text = view.findViewById(R.id.edt_text_ask)
       radioGroup = view.findViewById(R.id.radiogroup_ask)
       spinner = view.findViewById(R.id.spinner_ask)
       btn_send = view.findViewById(R.id.btn_send_ask)

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

        btn_send.setOnClickListener(::sendAsk)
    }

    private fun fetchData(){
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
    }

     private fun sendAsk(v: View?) {
         val title = edt_title.editText?.text.toString()
         val text  = edt_text.editText?.text.toString()
         val spinnerSelected = if(spinnerData.size == 0) "" else spinner.selectedItem as String

         val checkdId = if(radioGroup.checkedRadioButtonId == R.id.radio_ask_free) 0 else 1
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

         if(spinnerSelected.isBlank()) {
             isValid = false
             Toast.makeText(requireContext(), "تمامی ورودی ها را پر کنید", Toast.LENGTH_SHORT).show()
         }

         if(!isValid)
             return

         questionViewModel.ask(title , text , checkdId , spinnerSelected).observe(viewLifecycleOwner , Observer {
             when(it.status){
                 Status.ERROR -> log("Error in Ask | msg : ${it.message}")
                 Status.LOADING -> log("Loading Ask ..")
                 Status.SUCCEESS ->{
                     if (it.data?.code!! > 300 || it.data.courses == null){
                         Toast.makeText(requireContext() , it.data.msg , Toast.LENGTH_SHORT).show()
                         return@Observer
                     }

                     Toast.makeText(requireContext() , it.data.msg , Toast.LENGTH_SHORT).show()
                 }
             }
         })
    }

}