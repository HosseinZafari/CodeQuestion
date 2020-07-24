package hosseinzafari.github.framework.core.ui.fragment

import androidx.fragment.app.Fragment
import hosseinzafari.github.framework.core.app.G

/*

@created in 25/06/2020 - 11:47 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

open class GFragment : Fragment() {

    override fun onResume() {
        super.onResume()

        G.currentFragment = this
    }
}