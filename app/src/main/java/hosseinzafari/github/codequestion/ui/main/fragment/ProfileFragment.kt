package hosseinzafari.github.codequestion.ui.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.adapter.ProfileViewPagerAdapter
import hosseinzafari.github.codequestion.data.memory.SaveInMemory
import hosseinzafari.github.codequestion.struct.ProfileViewPagerModel
import hosseinzafari.github.codequestion.ui.struct.UserModel
import hosseinzafari.github.codequestion.ui.viewmodel.ProfileViewModel
import hosseinzafari.github.framework.core.ui.fragment.GFragment


class ProfileFragment : GFragment() {

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tablayout  = view.findViewById<TabLayout>(R.id.tablayout_profile)
        val viewpager  = view.findViewById<ViewPager>(R.id.viewpager_profile)
        val txt_name    = view.findViewById<TextView>(R.id.txt_profile_name)

        val adapter = ProfileViewPagerAdapter(childFragmentManager , listOf(
            ProfileViewPagerModel(FactoryFragment.ABOUT_FRAGMENT   , "درباره") ,
            ProfileViewPagerModel(FactoryFragment.ACCOUNT_FRAGMENT , "مشخصات حساب کاربری") ,
        ))

        viewpager.adapter = adapter
        tablayout.setupWithViewPager(viewpager)

        profileViewModel.getUser().observe(viewLifecycleOwner) {
            if (it == null) {
                txt_name.text = "لطفا وارد حساب کاربری خود شوید"
            } else {
                txt_name.text = it.name + " " + it.family
            }
        }
    }



}
