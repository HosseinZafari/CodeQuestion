package hosseinzafari.github.codequestion.ui.ui.main.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.framework.core.ui.activity.GAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : GAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.framelayout , getFragment(FactoryFragment.HOME_FRAGMENT)).addToBackStack("Home").commit()

        // Setup Navigation View
        navigation_view.initWithSaveInstanceState(savedInstanceState)
        initNavigationView()
    }

    private fun getFragment(byte: Byte): Fragment {
        return FactoryFragment.getFragment(byte)
    }

    // Prepare And Customize Navigation View
    private fun initNavigationView(){
        navigation_view.apply { 
            addSpaceItem(SpaceItem("پروفایل" , R.drawable.ic_people_white_24dp))
            addSpaceItem(SpaceItem("کد ها" , R.drawable.ic_code_white_24dp))
            addSpaceItem(SpaceItem("سوالات" , R.drawable.ic_question_white_24dp))
            addSpaceItem(SpaceItem("خانه" , R.drawable.ic_home_white_24dp))
            showIconOnly()
            setSpaceOnClickListener(object  : SpaceOnClickListener{
                override fun onCentreButtonClick() {
                    log("onCentreButton Clicked!")
                }
    
                override fun onItemReselected(itemIndex: Int, itemName: String?) {
                    log("onItemReselected itemIndex $itemIndex , itemName $itemName")
                }
    
                override fun onItemClick(itemIndex: Int, itemName: String?) {
                    log("onItemClick itemIndex $itemIndex , itemName $itemName")
                }
            })
        }
    }
}
