package hosseinzafari.github.codequestion.ui.main.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.daimajia.androidanimations.library.Techniques
import com.facebook.drawee.view.SimpleDraweeView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.helper.toast
import hosseinzafari.github.codequestion.ui.ui.main.fragment.CodeFragment
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.CodeViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment
import io.github.kbiakov.codeview.CodeView
import io.github.kbiakov.codeview.adapters.Options
import io.github.kbiakov.codeview.highlight.ColorTheme
import io.github.kbiakov.codeview.highlight.Font

/*

@created in 25/08/2020 - 12:35 PM
@project Code Question
@author Hossein Zafari
@email  hosseinzafari2000@gmail.com
*/

class DetailCodeFragment : GFragment() {

    private var codeModelSelected: CodeModel? = null
    private val codeViewModel: CodeViewModel by viewModels()
    private var isShowProgress = false
    private lateinit var progressBar: ProgressBar
    private lateinit var txt_star: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        codeModelSelected = arguments?.getParcelable<CodeModel>(CodeFragment.KEY_CODE_MODEL)
        return inflater.inflate(R.layout.fragment_detail_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
    }

    private fun setupViews(view: View) {
        val txt_title = view.findViewById<TextView>(R.id.txt_detail_code_title)
        val txt_text = view.findViewById<TextView>(R.id.txt_detail_code_text)
        val img_profile = view.findViewById<SimpleDraweeView>(R.id.img_detail_code_profile)
        val codeView = view.findViewById<CodeView>(R.id.codeview_detail_code)
        val btn_showsource = view.findViewById<Button>(R.id.btn_detail_code_showsource)
        val btn_add_score = view.findViewById<ImageButton>(R.id.btn_add_score)
        val btn_minus_score = view.findViewById<ImageButton>(R.id.btn_minus_score)
        txt_star = view.findViewById(R.id.txt_detail_code_star)
        progressBar = view.findViewById(R.id.prg_change_point)

        var isShowSource = false

        log("CODEMODEL IS : " + codeModelSelected)

        if (codeModelSelected == null)
            return

        txt_title.text = codeModelSelected!!.title
        txt_text.text = codeModelSelected!!.text
        txt_star.text = codeModelSelected!!.codePoint

        if (codeModelSelected!!.image == null) {
            val imgResource = SaveInMemory.Resource.genderResource[codeModelSelected!!.gender] ?: 0
            img_profile.setActualImageResource(imgResource)
        } else {
            val imgUrl = Uri.parse(codeModelSelected!!.image.toString())
            img_profile.setImageURI(imgUrl)
        }

        btn_showsource.setOnClickListener {
            if (isShowSource)
                return@setOnClickListener

            isShowSource = true
            codeView.setOptions(
                Options.get(requireContext())
                    .withCode(codeModelSelected!!.source!!)
                    .withFont(Font.Consolas)
                    .withLanguage("java")
                    .withTheme(ColorTheme.MONOKAI)
            )
        }

        btn_add_score.setOnClickListener {
            displayProgress(true)
            codeViewModel.changeScore(true, codeModelSelected!!.codeId!!.toInt()).observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.ERROR -> {
                        log("Error ${it.message}")
                        displayProgress(false)
                    }
                    Status.LOADING -> log("Loading ${it.message}")
                    Status.SUCCEESS -> {
                        if (it.data == null || it.data.code >= 300) {
                            displayProgress(false)
                            toast(it.data!!.msg)
                            return@observe
                        }

                        displayProgress(false)
                        log("Success ${it.data}")
                        txt_star.text = (txt_star.text.toString().toInt() + 1).toString()
                    }
                }
            }
        }

        btn_minus_score.setOnClickListener {
            displayProgress(true)
            codeViewModel.changeScore(false, codeModelSelected!!.codeId!!.toInt()).observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.ERROR -> {
                        log("Error ${it.message}")
                        displayProgress(false)
                    }
                    Status.LOADING -> log("Loading ${it.message}")
                    Status.SUCCEESS -> {
                        if (it.data == null || it.data.code >= 300) {
                            displayProgress(false)
                            toast(it.data!!.msg)
                            return@observe
                        }

                        displayProgress(false)
                        toast(it.data.msg)
                        txt_star.text = (txt_star.text.toString().toInt() - 1).toString()
                    }
                }
            }
        }
    }

    private fun displayProgress(visible: Boolean) = if (visible) {
        progressBar.visibility = View.VISIBLE
        txt_star.visibility = View.GONE
    } else {
        progressBar.visibility = View.GONE
        txt_star.visibility = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getContainerFragment().anim(Techniques.SlideInRight)
    }

}