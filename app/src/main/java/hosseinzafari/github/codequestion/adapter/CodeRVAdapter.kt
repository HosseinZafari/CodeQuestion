package hosseinzafari.github.codequestion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.framework.core.app.G

/*

@created in 26/07/2020 - 06:52 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class CodeRVAdapter(val block:(String)-> Unit) : RecyclerView.Adapter<CodeRVAdapter.CodeViewHolder>() {

    var data : MutableList<CodeModel> = mutableListOf()
        private set

    fun updateData(data: List<CodeModel>?){
        data ?: return
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CodeRVAdapter.CodeViewHolder {
        val view = LayoutInflater.from(G.getContext()).inflate(R.layout.item_rv_bestcode , parent , false)
        return CodeViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CodeRVAdapter.CodeViewHolder , position: Int)  =
        data[position].let { code ->
            holder.onBind(code)
            holder.root.setOnClickListener { block(code.codeId) }
        }

    class CodeViewHolder(
        val root : View ,
        val txt_title_code: TextView = root.findViewById(R.id.txt_title_code),
        val txt_source_code: TextView = root.findViewById(R.id.txt_source_code) ,
        val txt_point: TextView = root.findViewById(R.id.txt_point)
    ) : RecyclerView.ViewHolder(root) {

        fun onBind(courseModel: CodeModel){
            txt_title_code.text = courseModel.title
            txt_source_code.text = courseModel.source
            txt_point.text = courseModel.codePoint
        }
    }
}
