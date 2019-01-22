package app.mobify.orderbreadandroid.activities.cart

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import app.mobify.orderbreadandroid.R
import app.mobify.orderbreadandroid.activities.base.BaseActivity
import app.mobify.orderbreadandroid.activities.checkout.CheckoutActivity
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStore
import app.mobify.orderbreadandroid.utils.repository.Repository
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPref
import app.mobify.orderbreadandroid.utils.views.cutomStartActivity
import kotlinx.android.synthetic.main.activity_cart.*
import org.koin.android.ext.android.inject
import java.math.BigDecimal

class CartActivity : BaseActivity(), CartContract.View {
    override fun errorRemoveItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun errorUpdateTotal() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val repository: Repository by inject()
    private val presenter: CartPresenter by inject()
    private val sharedPref: SharedPref by inject()
    private val memoryStore: MemoryStore by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        rvCartItems.layoutManager = LinearLayoutManager(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bCheckout.setOnClickListener {
            presenter.checkout()
        }
    }

    override fun onStart() {
        super.onStart()

        repository.base = this
        presenter.repository = repository
        presenter.sharedPref = sharedPref
        presenter.memoryStore = memoryStore
        presenter.view = this

        presenter.loadData()
    }

    override fun showCartItems(breads: MutableList<Bread>) {
        rvCartItems.adapter = CartAdapter(this, breads, presenter,
            {
                presenter.updateTotalValue(it)
            },
            {
                presenter.removeItem(it)
            })
    }

    override fun showTotalValue(total: BigDecimal) {
        bCheckout.text = resources.getString(R.string.cart_button_checkout, total)
    }

    override fun showCheckout() {
        cutomStartActivity(CheckoutActivity::class.java)
    }

    override fun errorMaxPerItem(maxPerItem: Int) {
        showError(getString(R.string.cart_error_max_per_item, maxPerItem))
    }

    override fun onPause() {
        super.onPause()
        repository.disposable?.dispose()
    }
}
