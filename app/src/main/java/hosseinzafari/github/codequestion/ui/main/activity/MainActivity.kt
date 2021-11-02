package hosseinzafari.github.codequestion.ui.ui.main.activity

import android.os.Bundle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.daimajia.androidanimations.library.Techniques
import com.google.android.material.navigation.NavigationView
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceNavigationView
import com.luseen.spacenavigation.SpaceOnClickListener
import hosseinzafari.github.codequestion.R
import hosseinzafari.github.codequestion.ui.helper.anim
import hosseinzafari.github.codequestion.ui.helper.log
import hosseinzafari.github.codequestion.ui.main.fragment.AnswerFragment
import hosseinzafari.github.codequestion.ui.main.fragment.ContainerFragment
import hosseinzafari.github.codequestion.ui.ui.main.fragment.FactoryFragment
import hosseinzafari.github.codequestion.ui.ui.main.fragment.HomeFragment
import hosseinzafari.github.codequestion.ui.viewmodel.MainViewModel
import hosseinzafari.github.framework.core.ui.activity.GAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : GAppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var navigationView: SpaceNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, SavedStateViewModelFactory(this.application, this)).get(MainViewModel::class.java)
        mainViewModel.prepareTokenInMemory()

        // init first fragment
        framelayout.anim(Techniques.FadeIn , 400)
        ContainerFragment.addFragment(this , FactoryFragment.HOME_FRAGMENT)
        (ContainerFragment.getFragment(FactoryFragment.HOME_FRAGMENT) as HomeFragment).onClickShowCodes = {
            navigationView.changeCurrentItem(1)
        }

        // Setup Navigation View
        navigationView = findViewById(R.id.navigation_view)
        navigationView.initWithSaveInstanceState(savedInstanceState)
        initNavigationView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    // Prepare And Customize Navigation View
    private fun initNavigationView(){
        navigationView.apply {
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
                            (ContainerFragment.getFragment(FactoryFragment.HOME_FRAGMENT) as HomeFragment).onClickShowCodes = {
                                navigationView.changeCurrentItem(1)
                            }
                        }

                        2-> {
                            framelayout.anim(Techniques.FadeIn , 400)
                            ContainerFragment.replaceFragment(this@MainActivity , FactoryFragment.QUESTION_FRAGMENT)
                        }

                        1-> {
                            framelayout.anim(Techniques.FadeIn , 400)
                            ContainerFragment.replaceFragment(this@MainActivity , FactoryFragment.CODE_FRAGMENT)
                        }

                        0-> {
                            framelayout.anim(Techniques.FadeIn , 400)
                            ContainerFragment.replaceFragment(this@MainActivity , FactoryFragment.PROFILE_FRAGMENT)
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

        if(getFragmentByTag("Answer") != null){
            framelayout.anim(Techniques.SlideInLeft)
        }

        if(getFragmentByTag("QuestionInAnswerFragment") != null){
            framelayout.anim(Techniques.SlideInLeft)
            val answer = ContainerFragment.getFragment(FactoryFragment.ANSWER_FRAGMENT) as AnswerFragment
            answer.isShowAnimation = false
        }

        if(getFragmentByTag("Bookmark") != null){
            log("Bookmark must delete")
//            ContainerFragment.deleteFragment(this , FactoryFragment.BOOKMARK_FRAGMENT)
            framelayout.anim(Techniques.SlideInLeft)
        }

        if(getFragmentByTag("AddCode") != null){
            framelayout.anim(Techniques.SlideInLeft)
        }

        if(getFragmentByTag("DetailCourse") != null){
            ContainerFragment.deleteFragment(this , FactoryFragment.DETAIL_COURSE_FRAGMENT)
            framelayout.anim(Techniques.SlideInLeft)
        }

        if(getFragmentByTag("BookmarkCourseToDetailCourse") != null){
            log("BookmarkCourseToDetailCourse must delete")
            ContainerFragment.deleteFragment(this , FactoryFragment.DETAIL_COURSE_FRAGMENT)
//            ContainerFragment.addFragment(this , FactoryFragment.BOOKMARK_FRAGMENT , tag = "Bookmark")
            framelayout.anim(Techniques.SlideInLeft)
        }

        if(getFragmentByTag("AllCourseToDetailCourse") != null){
            framelayout.anim(Techniques.FadeOut , 350)
        }

        if(getFragmentByTag("HomeFragmentToAllCourseFragment") != null){
            framelayout.anim(Techniques.SlideInLeft)
        }


        super.onBackPressed()
    }
}
