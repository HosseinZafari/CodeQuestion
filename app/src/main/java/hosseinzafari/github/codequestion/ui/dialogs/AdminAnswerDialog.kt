package hosseinzafari.github.codequestion.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.ButtonBarLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.AnswerModel
import hosseinzafari.github.framework.core.app.G

/*

@created in 31/10/2021 - 10:44 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AdminAnswerDialog(
    val answerdModel: AnswerModel ,
    val onSubmit: (AnswerModel) -> Unit
) : DialogFragment() {
    private lateinit var btn_send: Button
    private lateinit var edt_answered: TextInputLayout
    private lateinit var txt_title: MaterialTextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val rootView = G.layoutInflater.inflate(R.layout.dialog_admin_answer , null)
        setupViews(rootView)
        
        val dialog = AlertDialog.Builder(activity).apply {
            setView(rootView)
            setCancelable(false)
        }.create()

        return dialog
    }


    private fun setupViews(rootView: View) {
        txt_title = rootView.findViewById<MaterialTextView>(R.id.txt_answer_dialog_title)
        edt_answered = rootView.findViewById<TextInputLayout>(R.id.edt_answer_admin_asnwered)
        btn_send = rootView.findViewById<Button>(R.id.btn_answer_admin_send)


        txt_title.text = answerdModel.title
        btn_send.setOnClickListener {
            answerdModel.text = edt_answered.editText?.text.toString()
            answerdModel.toUser = answerdModel.fromUser
            onSubmit(answerdModel)
        }
    }


}