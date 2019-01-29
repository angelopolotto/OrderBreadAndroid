package app.mobify.orderbreadandroid.utils.repository

import app.mobify.orderbreadandroid.activities.base.BaseContract
import app.mobify.orderbreadandroid.api.endpoints.CieloEndpoint
import app.mobify.orderbreadandroid.api.endpoints.OrderBreadEndpoint
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.checkout.OrderVerification
import app.mobify.orderbreadandroid.api.models.cielo.CieloTransaction
import app.mobify.orderbreadandroid.api.models.cielo.CreditCard
import app.mobify.orderbreadandroid.api.models.cielo.Payment
import app.mobify.orderbreadandroid.utils.rxUtils.applySchedulers
import io.reactivex.disposables.Disposable
import java.math.BigDecimal

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

    override fun payOrder(merchantOrderId: String, amount: BigDecimal, creditCard: CreditCard, result) {
        //https://developercielo.github.io/manual/cielo-ecommerce#transa%C3%A7%C3%A3o-simples
        val transaction = CieloTransaction()
        transaction.merchantOrderId = merchantOrderId
        transaction.payment = Payment()
        transaction.payment!!.type = "CreditCard" // tipo de pagamento
        transaction.payment!!.amount = amount.multiply(BigDecimal(100)).toInt()
        transaction.payment!!.installments = 1 // número de parcelas
        transaction.payment!!.creditCard = creditCard
        cieloEndpoint.paySimple(transaction)
            .applySchedulers()
            .subscribe(
                { result(it) },
                { error ->
                    base.showError(error.message ?: "Error to load the data")
                    base.hideProgress()
                },
                { base.hideProgress() }
            )
    }

}