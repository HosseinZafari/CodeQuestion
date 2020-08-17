package hosseinzafari.github.codequestion.ui.ui.main.activity

import android.os.Bundle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.daimajia.androidanimations.library.Techniques
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.main.fragment.ContainerFragment
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.viewmodel.MainViewModel
import hosseinzafari.github.framework.core.ui.activity.GAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : GAppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, SavedStateViewModelFactory(this.application, this)).get(MainViewModel::class.java)
        mainViewModel.prepareTokenInMemory()

        // init first fragment
        framelayout.anim(Techniques.FadeIn , 400)
        ContainerFragment.addFragment(this , FactoryFragment.HOME_FRAGMENT)

        // Setup Navigation View
        navigation_view.initWithSaveInstanceState(savedInstanceState)
        initNavigationView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    // Prepare And Customize Navigation View
    private fun initNavigationView(){
        navigation_view.apply {
            addSpaceItem(SpaceItem("پروفایل" , R.drawable.ic_people_white_24dp))
            addSpaceItem(SpaceItem("کد ها" , R.drawable.ic_code_white_24dp))
            addSpaceItem(SpaceItem("سوالات" , R.drawable.ic_question_white_24dp))
            addSpaceItem(SpaceItem("خانه" , R.drawable.ic_home_white_24dp))
            showIconOnly()
            changeCurrentItem(3)
            setSpaceOnClickListener(object  : SpaceOnClickListener{
                override fun onCentreButtonClick() {
                    log("onCentreButton Clicked!")
                }

                override fun onItemReselected(itemIndex: Int, itemName: String?) {
                    log("onItemReselected itemIndex $itemIndex , itemName $itemName")
                }

                override fun onItemClick(itemIndex: Int, itemName: String?) {
                    log("onItemClick itemIndex $itemIndex , itemName $itemName")
                    when(itemIndex){
                        3-> {
                            framelayout.anim(Techniques.FadeIn , 400)
                            ContainerFragment.replaceFragment(this@MainActivity , FactoryFragment.HOME_FRAGMENT)
                        }

                        2-> {
                            framelayout.anim(Techniques.FadeIn , 400)
                            ContainerFragment.replaceFragment(this@MainActivity , FactoryFragment.QUESTION_FRAGMENT)
                        }
                    }
                }
            })
        }
    }

    private fun getFragmentByTag(tag: String) = supportFragmentManager.findFragmentByTag(tag)

    override fun onBackPressed() {
        if (getFragmentByTag("Rules") != null){
            framelayout.anim(Techniques.SlideInLeft)
        }

        if(getFragmentByTag("Ask") != null){
            framelayout.anim(Techniques.SlideInLeft)
        }

        super.onBackPressed()
    }
}
