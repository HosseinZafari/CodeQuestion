package hosseinzafari.github.codequestion.adapter

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.framework.core.app.G
import hosseinzafari.github.framework.extensions.inflate
import io.github.kbiakov.codeview.CodeView
import io.github.kbiakov.codeview.adapters.Options
import io.github.kbiakov.codeview.highlight.ColorTheme
import io.github.kbiakov.codeview.highlight.Font

class PendingCodeAdapter(
    var data: List<CodeModel> = listOf() ,
    val onSubmitCode: (CodeModel) -> Unit = {} ,
)  : RecyclerView.Adapter<PendingCodeAdapter.PendingListViewHolder>() {


    fun updateData(newData: List<CodeModel>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  =
        PendingListViewHolder(parent.inflate(R.layout.item_rv_pending_list ))

    override fun onBindViewHolder(holder: PendingListViewHolder, position: Int) {
        val codeModel = data[position]
        holder.apply {
            val imgUrl = Uri.parse(codeModel.image.toString())
            imageView.setImageRequest(ImageRequest.fromUri(imgUrl))

            txt_title.text = codeModel.title
            txt_desc.text = codeModel.text
            var isShowSource = false

            btn_show.setOnClickListener {
                if (isShowSource)
                    return@setOnClickListener

                isShowSource = true
                codeView.setOptions(
                    Options.get(G.getContext())
                        .withCode(codeModel.source!!)
                        .withFont(Font.Consolas)
                        .withLanguage("java")
                        .withTheme(ColorTheme.MONOKAI)
                )
            }

            btn_submit_code.setOnClickListener {
                onSubmitCode(codeModel)
            }

        }

    }

    override fun getItemCount() = data.size

    class PendingListViewHolder(
        val root: View,
        val imageView: SimpleDraweeView = root.findViewById(R.id.pending_img_detail_code_profile),
        val txt_title: TextView         = root.findViewById(R.id.pending_txt_detail_code_title),
        val txt_desc: TextView          = root.findViewById(R.id.pending_txt_detail_code_text),
        val codeView: CodeView          = root.findViewById(R.id.pending_codeview_detail_code),
        val btn_show: Button            = root.findViewById(R.id.pending_btn_detail_code_showsource) ,
        val btn_submit_code: Button     = root.findViewById(R.id.btn_submit_code) ,
    ) : RecyclerView.ViewHolder(root)
}
