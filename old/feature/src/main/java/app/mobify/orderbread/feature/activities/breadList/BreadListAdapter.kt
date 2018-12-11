package app.mobify.orderbread.feature.activities.breadList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.mobify.orderbread.feature.R
import app.mobify.orderbread.feature.api.models.Bread
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.bread_list_item.view.*

class BreadListAdapter(val context: Context,
                       val items: ArrayList<Bread>,
                       val onClick: (item: Bread) -> Unit): RecyclerView.Adapter<BreadListAdapter.BreadListHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreadListHolder {
        return BreadListHolder(LayoutInflater.from(context).inflate(R.layout.bread_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BreadListHolder, position: Int) {
        val item = items.get(position)
        holder.tvName?.text = item.name
        Glide.with(context).load(item.thumbnail).into(holder.ivThumbnail!!)

        holder.itemView.setOnClickListener { onClick(item) }
    }

    class BreadListHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView?.tvName
        var ivThumbnail = itemView?.ivThumbnail
    }
}