package hosseinzafari.github.codequestion.adapter

import android.view.View
import android.view.ViewGroup
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

class AnswerRVAdapter : RecyclerView.Adapter<AnswerRVAdapter.AnswerViewHolder>() {

    var data = listOf<AnswerModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        private const val ADMIN = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AnswerViewHolder(parent.inflate(R.layout.item_rv_answer))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answer = data[position]
        holder.apply {
            txt_title.text = answer.title
            txt_text.text = answer.text
            txt_answer_date.text = answer.date
            txt_course_type.text = answer.course

            if(answer.type.equals("1")){
                txt_type_question.text = G.getContext().resources.getText(R.string.answer_type_pay)
            } else {
                txt_type_question.text = G.getContext().resources.getText(R.string.answer_type_free)
            }

            if(answer.isAdmin == ADMIN) { // from admin
                txt_item_answer_state.text = G.getContext().resources.getText(R.string.answer_from_admin)
            } else { // from user
                txt_item_answer_state.text = G.getContext().resources.getText(R.string.question_from_user)
            }
        }
    }



    class AnswerViewHolder(
        val view: View,
        val txt_title: TextView = view.findViewById(R.id.txt_title_item_answer),
        val txt_text: TextView = view.findViewById(R.id.txt_text_item_answer),
        val txt_course_type: TextView = view.findViewById(R.id.txt_item_answer_coursetype),
        val txt_type_question: TextView = view.findViewById(R.id.txt_item_answer_type),
        val txt_answer_date: TextView = view.findViewById(R.id.txt_item_answer_date),
        val txt_item_answer_state: TextView = view.findViewById(R.id.txt_item_answer_state) ,
    ) : RecyclerView.ViewHolder(view)
}