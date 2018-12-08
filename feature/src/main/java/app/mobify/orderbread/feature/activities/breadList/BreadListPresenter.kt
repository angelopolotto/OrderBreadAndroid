package app.mobify.orderbread.feature.activities.breadList

import app.mobify.orderbread.feature.api.models.Bread
import app.mobify.orderbread.feature.utils.memoryStore.MemoryStoreContract
import app.mobify.orderbread.feature.utils.repository.RepositoryContract

class BreadListPresenter
    : BreadListContract.Presenter {
    lateinit var view: BreadListContract.View
    lateinit var repository: RepositoryContract
    lateinit var memoryStore: MemoryStoreContract

    override fun showDetailsBreads(bread: Bread) {
        memoryStore.bread = bread
        view.showDetails()
    }

    override fun loadBreads() {
        repository.loadBreads { breads -> view.showBreads(breads) }
    }
}