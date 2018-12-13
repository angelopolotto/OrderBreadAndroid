package app.mobify.orderbreadandroid.activities.cart

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.mobify.orderbreadandroid.R
import app.mobify.orderbreadandroid.api.models.Bread
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cart_item.view.*

class CartAdapter(
    private val context: Context,
    private val items: MutableList<Bread>,
    private val presenter: CartContract.Presenter,
    private val onChanged: (item: Bread) -> Unit,
    private val onRemove: (item: Bread) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder(LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val item = items[position]

        holder.tvBreadName?.text = item.name
        holder.tvPrice?.text = context.resources.getString(R.string.cart_item_price, item.price)
        holder.tvTotalPrice?.text =
                context.resources.getString(R.string.cart_item_total, presenter.calculateItemTotalPrice(item))

        holder.ccQuantity?.initialValueCounter = item.quantity
        holder.ccQuantity?.maxValueCounter = presenter.maxValuePerItem()
        holder.ccQuantity?.valueMaxValueListener { presenter.maxValuePerItemError() }
        holder.ccQuantity?.valueUpdatedListener {
            item.quantity = it
            holder.tvTotalPrice?.text =
                    context.resources.getString(R.string.cart_item_total, presenter.calculateItemTotalPrice(item))
            onChanged(item)
        }
        holder.ccQuantity?.itemRemoved {
            onRemove(item)
        }

        Glide.with(context).load(item.thumbnail).into(holder.ivBreadThumbnail!!)
    }

    class CartHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var ivBreadThumbnail = itemView?.ivBreadThumbnail
        var tvBreadName = itemView?.tvBreadName
        var tvPrice = itemView?.tvPrice
        var ccQuantity = itemView?.ccQuantity
        var tvTotalPrice = itemView?.tvTotalPrice
    }
}