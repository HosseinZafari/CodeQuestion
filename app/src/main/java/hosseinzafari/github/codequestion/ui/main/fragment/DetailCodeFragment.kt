package hosseinzafari.github.codequestion.ui.main.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.daimajia.androidanimations.library.Techniques
import com.facebook.drawee.view.SimpleDraweeView
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.main.fragment.CodeFragment
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        codeModelSelected = arguments?.getParcelable<CodeModel>(CodeFragment.KEY_CODE_MODEL)
        return inflater.inflate(R.layout.fragment_detail_code , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
    }

    private fun setupViews(view: View){
        val txt_title = view.findViewById<TextView>(R.id.txt_detail_code_title)
        val txt_text = view.findViewById<TextView>(R.id.txt_detail_code_text)
        val txt_star = view.findViewById<TextView>(R.id.txt_detail_code_star)
        val img_profile = view.findViewById<SimpleDraweeView>(R.id.img_detail_code_profile)
        val btn_showsource = view.findViewById<Button>(R.id.btn_detail_code_showsource)
        val codeView = view.findViewById<CodeView>(R.id.codeview_detail_code)

        log("CODEMODEL IS : " + codeModelSelected)

        if(codeModelSelected == null)
            return

        txt_title.text = codeModelSelected!!.title
        txt_text.text = codeModelSelected!!.text
        txt_star.text = codeModelSelected!!.codePoint

        if(codeModelSelected!!.image == null){
            val imgResource = SaveInMemory.Resource.genderResource[codeModelSelected!!.gender] ?: 0
            img_profile.setActualImageResource(imgResource)
        } else {
            val imgUrl = Uri.parse(codeModelSelected!!.image.toString())
            img_profile.setImageURI(imgUrl)
        }

        var isShowSource = false
        btn_showsource.setOnClickListener {
            if(isShowSource)
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getContainerFragment().anim(Techniques.SlideInRight)
    }

    override fun onDetach() {
//        ContainerFragment.clearFragment(FactoryFragment.DETAIL_CODE_FRAGMENT) // clear from memory
        super.onDetach()
    }
}