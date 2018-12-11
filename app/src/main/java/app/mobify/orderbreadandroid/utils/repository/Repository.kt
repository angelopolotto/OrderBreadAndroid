package app.mobify.orderbreadandroid.utils.repository

import app.mobify.orderbreadandroid.api.BreadApiService
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.activities.base.BaseContract
import app.mobify.orderbreadandroid.utils.rxUtils.applySchedulers
import io.reactivex.disposables.Disposable

class Repository : RepositoryContract {
    lateinit var base: BaseContract.View

    val breadApiServe by lazy {
        BreadApiService.create()
    }
    var disposable: Disposable? = null

    override fun loadBreads(result: (breads: ArrayList<Bread>) -> Unit) {
        base.showProgress()
        disposable =
                breadApiServe.breads()
                    .applySchedulers()
                    .subscribe(
                        { result(it) },
                        { error ->
                            base.showError(error.message ?: "Error to load the data")
                            base.hideProgress()
                        },
                        {  base.hideProgress() }
                    )
    }
}