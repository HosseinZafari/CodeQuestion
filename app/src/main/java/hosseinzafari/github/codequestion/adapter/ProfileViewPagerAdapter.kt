package hosseinzafari.github.codequestion.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hosseinzafari.github.codequestion.struct.ProfileViewPagerModel
import hosseinzafari.github.codequestion.ui.main.fragment.ContainerFragment

/*

@created in 02/09/2020 - 04:54 PM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class ProfileViewPagerAdapter(fm: FragmentManager , val profileModel: List<ProfileViewPagerModel>) : FragmentPagerAdapter(fm , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = profileModel.size

    override fun getItem(position: Int) = ContainerFragment.getFragment(profileModel[position].fragmentId)

    override fun getPageTitle(position: Int) = profileModel[position].title
}