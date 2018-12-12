package app.mobify.orderbreadandroid.activities.cart

import android.os.Bundle
import app.mobify.orderbreadandroid.R
import app.mobify.orderbreadandroid.activities.base.BaseActivity
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.utils.repository.Repository
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPref
import org.koin.android.ext.android.inject

class CartActivity : BaseActivity(), CartContract.View {
    private val repository: Repository by inject()
    private val presenter: CartPresenter by inject()
    private val sharedPref: SharedPref by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }

    override fun onStart() {
        super.onStart()

        repository.base = this
        presenter.repository = repository
        presenter.sharedPref = sharedPref
        presenter.view = this

        presenter.loadData()
    }

    override fun onPause() {
        super.onPause()
        repository.disposable?.dispose()
    }

    override fun showCartItems(breads: MutableList<Bread>) {

    }
}
