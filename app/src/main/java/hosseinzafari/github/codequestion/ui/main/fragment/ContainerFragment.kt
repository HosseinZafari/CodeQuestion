package hosseinzafari.github.codequestion.ui.main.fragment

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.framework.core.ui.fragment.GFragment

/*

@created in 13/08/2020 - 10:35 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

object ContainerFragment {
    val fragments = mutableMapOf<Byte, GFragment>()

    private fun showFragment(
        activity: FragmentActivity,
        target: Byte,
        backStack: Boolean,
        name: String? = null,
        block: (FragmentTransaction, GFragment) -> Unit
    ) {
        val beginTransaction = getTransaction(activity)
        if(backStack) {
            beginTransaction.addToBackStack(name)
        }

        block(beginTransaction , getFragment(target))
    }


    fun addFragment(
        activity: FragmentActivity,
        targetFragment: Byte,
        containerLayout: Int = R.id.framelayout,
        tag: String? = null
    ) = showFragment(activity, targetFragment, false) { transaction, fragment ->
        transaction.add(containerLayout, fragment, tag).commit()
    }

    fun replaceFragment(
        activity: FragmentActivity,
        targetFragment: Byte,
        containerLayout: Int = R.id.framelayout,
        tag: String? = null
    ) = showFragment(activity, targetFragment, false) { transaction, fragment ->
        transaction.replace(containerLayout, fragment, tag).commit()
    }


    fun replaceFragmentWithBack(
        activity: FragmentActivity,
        targetFragment: Byte,
        containerLayout: Int = R.id.framelayout,
        tag: String? = null
    ) = showFragment(activity, targetFragment, true , name = tag) { transaction, fragment ->
        transaction.replace(containerLayout, fragment, tag).commit()
    }

    fun getFragment(target: Byte): GFragment {
        if(fragments[target] == null) {
            fragments[target] = FactoryFragment.getFragment(target)
        } else {
            return fragments[target]!!
        }
        log("getFragment() Size is : ${fragments.size}")
        return fragments[target]!!
    }


    fun getTransaction(actvity: FragmentActivity) = actvity.supportFragmentManager.beginTransaction()


    fun clearFragment(target: Byte) {
        fragments.remove(target)
    }


    fun clearAllFragment() = fragments.clear()

}