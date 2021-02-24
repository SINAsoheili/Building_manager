package ir.sinasoheili.building_manager.VIEW

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import ir.sinasoheili.building_manager.MainActivity
import ir.sinasoheili.building_manager.PRESENTER.UserAuthFilePreferenceHandler
import ir.sinasoheili.building_manager.R

class IntroSliderActivity : AppCompatActivity(), View.OnClickListener,
    ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private lateinit var btnNext: Button
    private lateinit var btnPrev: Button
    private lateinit var dotList: Array<TextView?>
    private lateinit var dotContiner: LinearLayout

    private val pages: IntArray = intArrayOf(R.layout.slide1 , R.layout.slide2 , R.layout.slide3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider)

        init()
        initIntroSlider()
    }

    private fun init() {
        viewPager = findViewById(R.id.viwepager_introsliedr)
        viewPager.addOnPageChangeListener(this)

        btnNext = findViewById(R.id.introSlider_btn_next)
        btnNext.setOnClickListener(this)
        btnPrev = findViewById(R.id.introSlider_btn_prev)
        btnPrev.setOnClickListener(this)

        dotContiner = findViewById(R.id.ll_docContainer)

        dotList = arrayOfNulls<TextView?>(pages.size)
        updateDots()
    }

    private fun initIntroSlider() {
        val adapter: IntroSliderAdapter = IntroSliderAdapter(this , pages)
        viewPager.adapter = adapter
    }

    private fun updateDots() {
        dotContiner.removeAllViews()
        for (i in 0..dotList.size-1) {
            dotList[i] = TextView(this)
            if (i == viewPager.currentItem){
                dotList.get(i)?.text = Html.fromHtml("&#9679;").toString()
                dotList.get(i)?.textSize = 12F
            } else {
                dotList.get(i)?.text = Html.fromHtml("&#9675;").toString()
                dotList.get(i)?.textSize = 12F
            }
            dotContiner.addView(dotList.get(i))
        }
    }

    override fun onClick(v: View?) {
        when(v) {
            btnNext -> {
                if(viewPager.currentItem+1 < pages.size)
                    viewPager.setCurrentItem(viewPager.currentItem+1)
                else if(viewPager.currentItem == pages.size-1) {
                    startApp()
                }
            }

            btnPrev -> {
                if(viewPager.currentItem-1 >= 0)
                    viewPager.setCurrentItem(viewPager.currentItem-1)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if (position > 0) {
            btnPrev.visibility = View.VISIBLE
        } else {
            btnPrev.visibility = View.GONE
        }

        if(position == (pages.size-1)) {
            btnNext.text = getString(R.string.start)
        } else {
            btnNext.text = getString(R.string.next)
        }

        updateDots()
    }

    private fun startApp() {
        UserAuthFilePreferenceHandler.writeToFile(this , UserAuthFilePreferenceHandler.KEY_INTRO_VISIT_STATUS , UserAuthFilePreferenceHandler.KEY_INTRO_VISITED)
        startActivity(Intent(this , MainActivity::class.java))
        finish()
    }
}