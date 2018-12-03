package app.mobify.orderbread.feature.activities.breadDetails

import app.mobify.orderbread.feature.utils.`super`.MemStoreContract
import app.mobify.orderbread.feature.utils.repository.RepositoryContract

class BreadDetailsPresenter: BreadDetailsContract.Presenter {
    lateinit var repository: RepositoryContract
    lateinit var view: BreadDetailsContract.View
    lateinit var memStore: MemStoreContract

    override fun loadDetails() {
        view.showDetails(memStore.breadItem)
    }

}