package hosseinzafari.github.codequestion.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.AnswerModel
import hosseinzafari.github.framework.core.app.G
import hosseinzafari.github.framework.extensions.inflate

/*
    @created in 22/08/2020 - 01:14 PM
    @project Code Question
    @author Hossein Zafari
    @email  hosseinzafari2000@gmail.com
*/

class AdminAnswerRVAdapter(val onWrongItemClick: (AnswerModel , Int) -> Unit)
    : RecyclerView.Adapter<AdminAnswerRVAdapter.AdminAnswerViewHolder>() {

    var data = mutableListOf<AnswerModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    @SuppressLint("NotifyDataSetChanged")
    fun putNewData(newData: List<AnswerModel>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }


    companion object {
        private const val ADMIN = 1
        const val TYPE_WRONG = 0x1
        const val TYPE_ANSWERD = 0x2

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AdminAnswerRVAdapter.AdminAnswerViewHolder(parent.inflate(R.layout.item_rv_answer_admin))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AdminAnswerViewHolder, position: Int) {
        val answer = data[position]
        holder.apply {
            txt_title.text       = answer.title
            txt_text.text        = answer.text
            txt_answer_date.text = answer.date
            txt_course_type.text = answer.course
            txt_name.text        = answer.name
            
            if(answer.type.equals("1")){
                txt_type_question.text = G.getContext().resources.getText(R.string.answer_type_pay)
            } else {
                txt_type_question.text = G.getContext().resources.getText(R.string.answer_type_free)
            }


            if(answer.answered!!) {
                txt_answer_answered.visibility = View.VISIBLE
                btn_answer_send.visibility = View.GONE
            } else {
                txt_answer_answered.visibility = View.GONE
                btn_answer_send.visibility = View.VISIBLE
            }
            
            btn_answer_wrong.setOnClickListener {
                onWrongItemClick(answer , TYPE_WRONG) // TODO set color for returned or no returend
                btn_answer_wrong.setTextColor(Color.parseColor("#777777"))
            }

            btn_answer_send.setOnClickListener {
                onWrongItemClick(answer , TYPE_ANSWERD)
            }
        }
    }



    class AdminAnswerViewHolder(
        val view: View,
        val txt_name: TextView = view.findViewById(R.id.txt_name_title_admin_answer),
        val txt_title: TextView = view.findViewById(R.id.txt_title_item_answer_admin),
        val txt_text: TextView = view.findViewById(R.id.txt_text_item_admin_answer),
        val txt_course_type: TextView = view.findViewById(R.id.txt_item_admin_answer_coursetype),
        val txt_type_question: TextView = view.findViewById(R.id.txt_item_admin_answer_type),
        val txt_answer_date: TextView = view.findViewById(R.id.txt_item_admin_answer_date),
        val btn_answer_wrong: Button = view.findViewById(R.id.btn_wrong_question_admin_answer),
        val btn_answer_send: Button = view.findViewById(R.id.btn_answer_admin),
        val txt_answer_answered: TextView = view.findViewById(R.id.txt_message_state_admin),
    ) : RecyclerView.ViewHolder(view)
}