package app.mobify.orderbread.feature.activities.breadList

import app.mobify.orderbread.feature.api.models.Bread
import app.mobify.orderbread.feature.api.models.Cart
import app.mobify.orderbread.feature.utils.memoryStore.MemoryStoreContract
import app.mobify.orderbread.feature.utils.repository.RepositoryContract
import app.mobify.orderbread.feature.utils.sharedPrefs.SharedPrefContract

class BreadListPresenter
    : BreadListContract.Presenter {
    lateinit var view: BreadListContract.View
    lateinit var repository: RepositoryContract
    lateinit var memoryStore: MemoryStoreContract
    lateinit var sharedPref: SharedPrefContract

    private var cart: Cart? = null

    override fun showDetailsBreads(bread: Bread) {
        memoryStore.bread = bread
        view.showDetails()
    }


    override fun loadData() {
        repository.loadBreads { breads -> view.showBreads(breads) }
        cart = sharedPref.getCart()

        cart?.let {
            val total = it.breads.fold(0) { sum, bread -> sum + bread.quantity }
            view.showCart(total)
        }
    }
}