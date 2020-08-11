package hosseinzafari.github.codequestion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.RulesModel
import hosseinzafari.github.framework.core.app.G

/*

@created in 26/07/2020 - 06:52 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class RulesRVAdapter() : RecyclerView.Adapter<RulesRVAdapter.RulesViewHolder>() {

    var data : MutableList<RulesModel> = mutableListOf()
        private set

    fun updateData(data: List<RulesModel>?){
        data ?: return
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RulesViewHolder {
        val view = LayoutInflater.from(G.getContext()).inflate(R.layout.item_rv_rules , parent , false)
        return RulesViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int)  =
        data[position].let {
            holder.txt_rule_item_rules.text = it.rule
        }

    class RulesViewHolder(
        val root : View ,
        val txt_rule_item_rules: TextView = root.findViewById(R.id.txt_rule_item_rules)
    ) : RecyclerView.ViewHolder(root)
}
