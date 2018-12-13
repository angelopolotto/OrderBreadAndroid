package app.mobify.orderbreadandroid.activities.checkout

import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStoreContract
import app.mobify.orderbreadandroid.utils.repository.RepositoryContract
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPrefContract

class CheckoutPresenter : CheckoutContract.Presenter {
    lateinit var repository: RepositoryContract
    lateinit var sharedPref: SharedPrefContract
    lateinit var view: CheckoutContract.View
    lateinit var memoryStore: MemoryStoreContract

    override fun loadData() {

    }
}