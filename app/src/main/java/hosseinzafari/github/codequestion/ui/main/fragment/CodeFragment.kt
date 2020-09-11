package hosseinzafari.github.codequestion.ui.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.AllCodeRVAdapter
import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.helper.toast
import hosseinzafari.github.codequestion.ui.main.fragment.ContainerFragment
import hosseinzafari.github.codequestion.ui.main.fragment.FragmentHelper
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.CodeViewModel
import hosseinzafari.github.framework.core.app.G
import hosseinzafari.github.framework.core.ui.fragment.GFragment


class CodeFragment : GFragment() {

    companion object {
        private const val POPUALR_TAB_ID: Int = 0
        private const val NEW_TAB_ID:     Int = 1
        const val KEY_CODE_MODEL = "CODE"
    }

    private var lastSelectedTabId = POPUALR_TAB_ID

    private lateinit var txt_news: TextView
    private lateinit var txt_popular: TextView
    private lateinit var rv_all_code: RecyclerView

    private val allCodeAdapter = AllCodeRVAdapter(::goDetailCodeFragment)
    private val _codeViewModle: CodeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
        fetchAllCode(POPUALR_TAB_ID)
    }

    private fun setupViews(view: View){
        val search = view.findViewById<TextInputLayout>(R.id.edt_code_search)
        txt_news    = view.findViewById(R.id.txt_code_tab_news)
        txt_popular = view.findViewById(R.id.txt_code_tab_popular)
        val fab_new_code = view.findViewById<FloatingActionButton>(R.id.fab_new_code)

        txt_popular.setOnClickListener {
            if(lastSelectedTabId == POPUALR_TAB_ID)
                return@setOnClickListener // Block

            changeSelectedTabState(POPUALR_TAB_ID)
            fetchAllCode(POPUALR_TAB_ID)
        }

        txt_news.setOnClickListener {
            if(lastSelectedTabId == NEW_TAB_ID)
                return@setOnClickListener // Block

            changeSelectedTabState(NEW_TAB_ID)
            fetchAllCode(NEW_TAB_ID)
        }

        fab_new_code.setOnClickListener {
            _codeViewModle.getToken().observe(viewLifecycleOwner) { token ->
                if(token == null) {
                    // Set Destination State
                    SaveInMemory.destination = FragmentHelper.Destination.CODE

                    uiUtil.getContainerFragment().anim(Techniques.SlideInRight)
                    ContainerFragment.replaceFragment(requireActivity() , FactoryFragment.LOGIN_FRAGMENT)
                } else {
                    ContainerFragment.replaceFragmentWithBack(requireActivity() , FactoryFragment.ADD_CODE_FRAGMENT , tag = "AddCode")
                }
            }
        }

        val rv_all_code = view.findViewById<RecyclerView>(R.id.rv_allcode)
        rv_all_code.adapter = allCodeAdapter
    }

    private fun fetchAllCode(category: Int){
        _codeViewModle.getAllCodes(category).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.ERROR -> log("Error ${it.message}")
                Status.LOADING -> log("Loading ${it.message}")
                Status.SUCCEESS -> {
                    if (it.data == null || it.data.code >= 300 || it.data.codes == null) {
                        toast("مشکلی در برقراری ارتباط وجود دارد لطفا بعداَ امتحان کنید")
                        return@observe
                    }

                    log("Success ${it.data}")
                    allCodeAdapter.data = it.data.codes
                    rv_all_code.anim(Techniques.SlideInUp)
                }
            }
        }
    }

    private fun changeSelectedTabState(index: Int){
        if(index == POPUALR_TAB_ID){
            itemSelected(txt_popular)
            itemNoSelected(txt_news)
            lastSelectedTabId = POPUALR_TAB_ID
        } else if(index == NEW_TAB_ID) {
            itemSelected(txt_news)
            itemNoSelected(txt_popular)
            lastSelectedTabId = NEW_TAB_ID
        }
    }

    private fun itemSelected(txt: TextView){
        txt.setTextColor(ContextCompat.getColor(G.getContext() , R.color.space_white))
        txt.setBackgroundColor(ContextCompat.getColor(G.getContext() , R.color.colorAccent))
    }

    private fun itemNoSelected(txt: TextView){
        txt.setTextColor(ContextCompat.getColor(G.getContext() , R.color.colorGray4))
        txt.setBackgroundColor(ContextCompat.getColor(G.getContext() , R.color.space_white))
    }

    private fun goDetailCodeFragment(id: String){
        for(codeModel in allCodeAdapter.data){
            // find code model item clicked
            if(codeModel.codeId == id){
                val argumentCodeModel = Bundle()
                argumentCodeModel.putParcelable(KEY_CODE_MODEL , codeModel)
                ContainerFragment.replaceFragmentWithBack(
                    requireActivity() ,
                    FactoryFragment.DETAIL_CODE_FRAGMENT ,
                    argument = argumentCodeModel
                )

                // finded and close foreach
                break;
            }
        }
    }
}
