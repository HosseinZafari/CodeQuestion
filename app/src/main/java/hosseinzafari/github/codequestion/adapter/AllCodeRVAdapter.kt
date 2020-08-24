package hosseinzafari.github.codequestion.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.framework.core.app.G

/*

@created in 26/07/2020 - 06:52 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AllCodeRVAdapter(val block:(String)-> Unit) : RecyclerView.Adapter<AllCodeRVAdapter.CodeViewHolder>() {

    var data : List<CodeModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AllCodeRVAdapter.CodeViewHolder {
        val view = LayoutInflater.from(G.getContext()).inflate(R.layout.item_rv_all_code , parent , false)
        return CodeViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: AllCodeRVAdapter.CodeViewHolder, position: Int)  =
        data[position].let { code ->
            holder.onBind(code)
            holder.root.setOnClickListener { block(code.codeId) }
        }

    class CodeViewHolder(
        val root : View ,
        val txt_item_code_title: TextView = root.findViewById(R.id.txt_item_code_title),
        val txt_item_code_text: TextView = root.findViewById(R.id.txt_item_code_text) ,
        val txt_item_code_star: TextView = root.findViewById(R.id.txt_item_code_star) ,
        val img_item_code_profile: SimpleDraweeView = root.findViewById(R.id.img_item_code_profile) ,
    ) : RecyclerView.ViewHolder(root) {

        private val genderResource = mapOf(
            "0" to R.drawable.user_man ,
            "1" to R.drawable.user_famale ,
        )

        fun onBind(codeModel: CodeModel){
            txt_item_code_title.text = codeModel.title
            txt_item_code_text.text = codeModel.source
            txt_item_code_star.text = codeModel.codePoint
            if(codeModel.image == null){
                val imgResource = genderResource[codeModel.gender] ?: 0
                img_item_code_profile.setActualImageResource(imgResource)
            } else {
                val imgUrl = Uri.parse(codeModel.image.toString())
                img_item_code_profile.setImageURI(imgUrl)
            }
        }


    }


}
