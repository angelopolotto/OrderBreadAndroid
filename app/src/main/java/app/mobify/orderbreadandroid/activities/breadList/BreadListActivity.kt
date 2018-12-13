package app.mobify.orderbreadandroid.activities.breadList

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import app.mobify.orderbreadandroid.R
import app.mobify.orderbreadandroid.activities.base.BaseActivity
import app.mobify.orderbreadandroid.activities.breadDetails.BreadDetailsActivity
import app.mobify.orderbreadandroid.activities.cart.CartActivity
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStore
import app.mobify.orderbreadandroid.utils.repository.Repository
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPref
import app.mobify.orderbreadandroid.utils.views.cutomStartActivity
import kotlinx.android.synthetic.main.activity_bread_list.*
import kotlinx.android.synthetic.main.custom_image_button.view.*
import org.koin.android.ext.android.inject

class BreadListActivity : BaseActivity(), BreadListContract.View {
    private val repository: Repository by inject()
    private val presenter: BreadListPresenter by inject()
    private val memoryStore: MemoryStore by inject()
    private val sharedPref: SharedPref by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bread_list)

        rvBreads.layoutManager = GridLayoutManager(this, 2)
        progress = pbLoading

        //todo adicionar botão no menu para acessar o perfil
    }

    override fun onStart() {
        super.onStart()

        cibCart.visibility = View.GONE

        repository.base = this
        presenter.repository = repository
        presenter.memoryStore = memoryStore
        presenter.sharedPref = sharedPref
        presenter.view = this

        presenter.loadData()
    }

    override fun onPause() {
        super.onPause()
        repository.disposable?.dispose()
    }

    override fun showBreads(breads: ArrayList<Bread>) {
        rvBreads.adapter = BreadListAdapter(this, breads) {
            presenter.showDetailsBreads(it)
        }
    }

    override fun showDetails() {
        cutomStartActivity(BreadDetailsActivity::class.java)
    }

    override fun showCart(total: Int) {
        cibCart.visibility = View.VISIBLE
        cibCart.tvText.text = resources.getString(R.string.bread_list_cart_button, total)
        cibCart.bClick.setOnClickListener { presenter.showCart() }
    }

    override fun showCartActivity() {
        cutomStartActivity(CartActivity::class.java)
    }
}
