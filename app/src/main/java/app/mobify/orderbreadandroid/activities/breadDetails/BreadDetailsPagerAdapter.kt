package app.mobify.orderbreadandroid.activities.breadDetails

import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.mobify.orderbreadandroid.R
import com.bumptech.glide.Glide

class BreadDetailsPagerAdapter(
    private val mContext: Context, @field:IdRes
        private val images: List<String>
) : PagerAdapter() {
    private var mLayoutInflater: LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun instantiateItem(container: ViewGroup, @IdRes position: Int): View {
        val itemView = mLayoutInflater.inflate(R.layout.page_bread, container, false)

        val ivBread = itemView.findViewById<ImageView>(R.id.ivBread)
        Glide.with(mContext).load(images[position]).into(ivBread)

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}