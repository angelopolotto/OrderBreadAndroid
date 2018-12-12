package app.mobify.orderbreadandroid.activities.cart

import app.mobify.orderbreadandroid.utils.repository.RepositoryContract
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPrefContract

class CartPresenter : CartContract.Presenter {
    override fun loadData() {
        sharedPref.getCart()?.let {
            view.showCartItems(it.breads)
        }
    }

    lateinit var repository: RepositoryContract
    lateinit var sharedPref: SharedPrefContract
    lateinit var view: CartContract.View
}