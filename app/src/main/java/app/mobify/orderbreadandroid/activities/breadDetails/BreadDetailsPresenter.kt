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

    override fun orderBread(onActivityResult: Boolean) {
        if (sharedPref.getProfile() == null && !onActivityResult) {
            view.startLogin()
        } else if (sharedPref.getProfile() != null) {
            sharedPref.addToCart(memoryStore.bread, memoryStore.maxPerItem, memoryStore.maxItemsCart,
                success = { view.addedToCart() },
                errorMaxPerItem = { view.errorMaxPerItem(memoryStore.maxPerItem) },
                errorMaxItemsCart = { view.errorMaxItemsCart(memoryStore.maxItemsCart) })
        }
    }
}