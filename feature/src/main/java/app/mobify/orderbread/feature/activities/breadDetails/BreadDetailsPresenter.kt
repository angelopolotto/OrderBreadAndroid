package app.mobify.orderbread.feature.activities.breadDetails

import app.mobify.orderbread.feature.utils.memoryStore.MemoryStoreContract
import app.mobify.orderbread.feature.utils.repository.RepositoryContract
import app.mobify.orderbread.feature.utils.sharedPrefs.SharedPrefContract

class BreadDetailsPresenter: BreadDetailsContract.Presenter {
    lateinit var repository: RepositoryContract
    lateinit var view: BreadDetailsContract.View
    lateinit var memoryStore: MemoryStoreContract
    lateinit var sharedPref: SharedPrefContract

    override fun loadDetails() {
        view.showDetails(memoryStore.bread)
    }

    override fun orderBread() {
        if (sharedPref.getUser() == null) {
            view.startLogin()
        } else {
            sharedPref.addToCart(memoryStore.bread)
            view.addedToCart()
        }
    }
}