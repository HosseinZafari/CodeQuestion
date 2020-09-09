package hosseinzafari.github.codequestion.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.struct.UserModel
import hosseinzafari.github.framework.core.app.G

/*

@created in 26/07/2020 - 06:52 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class BestUserRVAdapter(val block:(String?)-> Unit) : RecyclerView.Adapter<BestUserRVAdapter.BestUserViewHolder>() {

    var data : MutableList<UserModel> = mutableListOf()
        private set

    fun updateData(data: List<UserModel>?){
        data ?: return
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BestUserViewHolder {
        val view = LayoutInflater.from(G.getContext()).inflate(R.layout.item_rv_bestuser , parent , false)
        return BestUserViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: BestUserViewHolder, position: Int)  =
        data[position].let { user ->
            holder.onBind(user)
            holder.root.setOnClickListener { block(user.userId) }
        }

    class BestUserViewHolder(
        val root : View ,
        val txt_bestuser_name: TextView = root.findViewById(R.id.txt_bestuser_name),
        val txt_star_value: TextView = root.findViewById(R.id.txt_star_value),
        val img_user: SimpleDraweeView = root.findViewById(R.id.img_user)
    ) : RecyclerView.ViewHolder(root) {

        fun onBind(userModel: UserModel){
            txt_bestuser_name.text = userModel.name + " " + userModel.family
            txt_star_value.text = userModel.point

            if(userModel.image == null){
                if (userModel.gender == 0.toByte()) { // is Male
                    img_user.setActualImageResource(R.drawable.user_man)
                } else { // is Female
                    img_user.setActualImageResource(R.drawable.user_famale)
                }
            } else {
                img_user.setImageURI(Uri.parse(userModel.image))
            }
        }
    }
}
