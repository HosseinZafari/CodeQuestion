package hosseinzafari.github.codequestion.ui.util

import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import hosseinzafari.github.codequestion.R

/*

@created in 03/08/2020 - 10:33 AM
@project Code Question
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/

class UiUtil(val activity: FragmentActivity) {

    fun getContainerFragment() = activity.findViewById<FrameLayout>(R.id.framelayout)

}