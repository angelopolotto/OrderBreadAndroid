package app.mobify.orderbread.feature.activities.breadList

import app.mobify.orderbread.feature.api.models.BreadItem
import app.mobify.orderbread.feature.utils.`super`.MemStore
import app.mobify.orderbread.feature.utils.`super`.MemStoreContract
import app.mobify.orderbread.feature.utils.repository.RepositoryContract

class BreadListPresenter
    : BreadListContract.Presenter {
    lateinit var view: BreadListContract.View
    lateinit var repository: RepositoryContract
    lateinit var memStore: MemStoreContract

    override fun showDetailsBreads(bread: BreadItem) {
        memStore.breadItem = bread
        view.showDetails()
    }

    override fun loadBreads() {
        repository.loadBreads { breads -> view.showBreads(breads) }
    }
}