package app.mobify.orderbreadandroid.utils.repository

import app.mobify.orderbreadandroid.activities.base.BaseContract
import app.mobify.orderbreadandroid.api.endpoints.CieloEndpoint
import app.mobify.orderbreadandroid.api.endpoints.OrderBreadEndpoint
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.checkout.OrderVerification
import app.mobify.orderbreadandroid.utils.rxUtils.applySchedulers
import io.reactivex.disposables.Disposable

class Repository : RepositoryContract {
    lateinit var base: BaseContract.View

    private val orderBreadEndpoint by lazy {
        OrderBreadEndpoint.create()
    }

    private val cieloEndpoint by lazy {
        CieloEndpoint.create()
    }

    var disposable: Disposable? = null

    override fun loadBreads(result: (breads: ArrayList<Bread>) -> Unit) {
        base.showProgress()
        disposable =
                orderBreadEndpoint.breads()
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

    override fun allowCustomerLocation(
        lat: Double,
        long: Double,
        result: (orderVerification: OrderVerification) -> Unit
    ) {
    }


}