package app.mobify.orderbread.feature.breadList

import app.mobify.orderbread.feature.api.models.BreadItem
import app.mobify.orderbread.feature.repository.RepositoryContract

class BreadListPresenter
    : BreadListContract.Presenter {
    lateinit var view: BreadListContract.View
    lateinit var repository: RepositoryContract

    override fun showDetailsBreads(bread: BreadItem) {
        view.showDetails(bread)
    }

    override fun loadBreads() {
        repository.loadBreads { breads -> view.showBreads(breads) }
    }
}