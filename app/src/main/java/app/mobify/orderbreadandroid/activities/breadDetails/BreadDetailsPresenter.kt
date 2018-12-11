package app.mobify.orderbreadandroid.activities.breadDetails

import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStoreContract
import app.mobify.orderbreadandroid.utils.repository.RepositoryContract
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPrefContract

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