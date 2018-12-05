package app.mobify.orderbread.feature.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import app.mobify.orderbread.R

class CustomDots(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var dotsCount: Int = 0
    private var dots: Array<ImageView?>? = null
    private var pagerAdapter: PagerAdapter? = null
    private var viewPager: ViewPager? = null

    fun configureViewPager(viewPager: ViewPager, pagerAdapter: PagerAdapter) {
        this.viewPager = viewPager
        this.pagerAdapter = pagerAdapter

        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                Log.d("###onPageSelected, pos ", position.toString())
                for (i in 0 until dotsCount) {
                    dots!![i]!!.setImageDrawable(resources.getDrawable(R.drawable.nonselecteditem_dot))
                }
                dots!![position]!!.setImageDrawable(resources.getDrawable(R.drawable.selecteditem_dot))
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        viewPager.currentItem = 0

        setPageViewIndicator()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setPageViewIndicator() {
        Log.d("###setPageViewIndicator", " : called")

        this.gravity = Gravity.CENTER

        dotsCount = pagerAdapter!!.count
        dots = arrayOfNulls(dotsCount)

        for (i in 0 until dotsCount) {
            dots!![i] = ImageView(this.context)
            dots!![i]!!.setImageDrawable(resources.getDrawable(R.drawable.nonselecteditem_dot))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(
                resources.getDimension(R.dimen.margin_start_dots).toInt(), 0,
                resources.getDimension(R.dimen.margin_start_dots).toInt(), 0
            )

            dots!![i]!!.setOnTouchListener { _, event ->
                viewPager!!.currentItem = i
                true
            }
            this.addView(dots!![i], params)
        }
        dots!![0]!!.setImageDrawable(resources.getDrawable(R.drawable.selecteditem_dot))
    }
}
