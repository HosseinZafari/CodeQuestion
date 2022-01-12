package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.daimajia.androidanimations.library.Techniques
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.framework.core.ui.fragment.GFragment

class PendingFragment : GFragment() {

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ? {
        root = inflater.inflate(R.layout.fragment_pending , container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val cardViewPending = root.findViewById<CardView>(R.id.cardview_pending)
        val cardViewSearch  = root.findViewById<CardView>(R.id.cardview_search)

        cardViewPending.setOnClickListener {
            ContainerFragment.replaceFragmentWithBack(requireActivity() , FactoryFragment.PENDING_LIST_FRAGMENT , tag = "PendingList")
        }

        cardViewSearch.setOnClickListener {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        uiUtil.getLayoutRootFragment().anim(Techniques.SlideInRight)

    }

}