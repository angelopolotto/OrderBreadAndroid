package app.mobify.orderbread.feature.repository

import app.mobify.orderbread.feature.api.BreadApiService
import app.mobify.orderbread.feature.api.models.BreadItem
import app.mobify.orderbread.feature.base.BaseContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Repository : RepositoryContract {
    lateinit var base: BaseContract.View

    val breadApiServe by lazy {
        BreadApiService.create()
    }
    var disposable: Disposable? = null

    override fun loadBreads(result: (breads: ArrayList<BreadItem>) -> Unit) {
        disposable =
                breadApiServe.breads()
                    .subscribeOn(Schedulers .io())
                    .doOnNext {
                        base.showProgress()
                    }
                    .observeOn(AndroidSchedulers.mainThread())
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