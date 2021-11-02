package hosseinzafari.github.codequestion.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentFactory
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.framework.core.app.G
import hosseinzafari.github.framework.core.ui.fragment.GFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_account.*

/*

@created in 03/09/2020 - 10:33 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class AccountFragment : GFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews(view)
    }

    private fun setupViews(view: View) {
//        val tabLayout = view.findViewById<TabLayout>(R.id.tablayout_profile)
//        val txt_name = view.findViewById<TextView>(R.id.txt_profile_name)
//        val tabLayout = view.findViewById<TabLayout>(R.id.tablayout_profile)

        card_bookmarks.setOnClickListener { bookmarks() }

    }

    private fun bookmarks(){
        ContainerFragment.addFragmentWithBack(requireActivity() , FactoryFragment.BOOKMARK_FRAGMENT , tag = "Bookmark")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
}