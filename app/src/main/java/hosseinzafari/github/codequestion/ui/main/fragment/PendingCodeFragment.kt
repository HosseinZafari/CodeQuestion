package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.PendingCodeAdapter
import hosseinzafari.github.codequestion.struct.CodeModel
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.helper.toast
import hosseinzafari.github.codequestion.ui.ui.util.Status
import hosseinzafari.github.codequestion.ui.viewmodel.CodeViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment
import kotlinx.android.synthetic.main.fragment_detail_code.*

class PendingCodeFragment: GFragment() {

    private lateinit var rootView: View
    private lateinit var rv_pending_list: RecyclerView
    private lateinit var loading_pending_list: FrameLayout
    private lateinit var linear_layout_empty: LinearLayoutCompat
    private val codeViewModel: CodeViewModel by viewModels()
    private val pendingListAdapter = PendingCodeAdapter(onSubmitCode = ::onSubmitCode)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_pending_list , container , false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fetchData()
    }


    private fun setupViews() {
        rv_pending_list = rootView.findViewById(R.id.rv_pending_list)
        loading_pending_list = rootView.findViewById(R.id.prg_pending_list)
        linear_layout_empty = rootView.findViewById(R.id.linear_layout_empty)
        rv_pending_list.adapter = pendingListAdapter
     }

    private fun fetchData() {
        codeViewModel.getAllPendingCode().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.ERROR -> {
                    log("Error ${it.message}")
                    displayProgress(false)
                }
                Status.LOADING -> log("Loading ${it.message}")
                Status.SUCCEESS -> {
                    log("success " + it)
                    if (it.data == null) {
                        displayProgress(false)
                        toast("مشکلی در سرور رخ داده بعدا امتحان کنید")
                        return@observe
                    }


                    if(it.data.code != 404) {
                        pendingListAdapter.updateData(it.data.codes!!)
                            displayProgress(false)
                        if(it.data.codes.isEmpty()) {
                            showEmpty(true)
                        } else {
                            showEmpty(false)
                        }
                    }
                }
            }
        }
    }

    private fun showEmpty(show: Boolean) {
        if(show) {
            rv_pending_list.visibility = View.GONE
            linear_layout_empty.visibility = View.VISIBLE
        } else {
            rv_pending_list.visibility = View.VISIBLE
            linear_layout_empty.visibility = View.GONE
        }
    }

    private fun onSubmitCode(codeModel: CodeModel) {
        codeViewModel.updatePendingCode(codeModel.codeId!!.toInt()).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.ERROR -> {
                    log("Error ${it.message}")
                    displayProgress(false)
                }
                Status.LOADING -> log("Loading ${it.message}")
                Status.SUCCEESS -> {
                    log("success " + it)
                    if (it.data == null) {
                        displayProgress(false)
                        toast("مشکلی در سرور رخ داده بعدا امتحان کنید")
                        return@observe
                    }


                    if(it.data.code == 200) {
                        toast("با موفقیت کد مورد نظر تایید شد")
                        displayProgress(true)
                        fetchData()
                    }
                }
            }
        }
    }


    private fun displayProgress(isShow: Boolean) {
        if (!isShow) {
            rv_pending_list.visibility = View.VISIBLE
            loading_pending_list.visibility = View.GONE
        } else {
            rv_pending_list.visibility = View.GONE
            loading_pending_list.visibility = View.VISIBLE
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getLayoutRootFragment().anim(Techniques.SlideInRight , 200)
    }
}