package app.mobify.orderbread.feature.activities.breadDetails

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BreadDetailsPagerAdapter(
    private val mContext: Context, @field:IdRes
    private val pages: IntArray
) : PagerAdapter() {
    internal var mLayoutInflater: LayoutInflater

    init {
        mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun instantiateItem(container: ViewGroup, @IdRes position: Int): View {
        val itemView = mLayoutInflater.inflate(pages[position], container, false)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}