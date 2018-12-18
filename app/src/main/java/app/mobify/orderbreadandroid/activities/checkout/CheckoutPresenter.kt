package app.mobify.orderbreadandroid.activities.checkout

import app.mobify.orderbreadandroid.api.models.checkout.OrderVerification
import app.mobify.orderbreadandroid.api.models.user.Wallet
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStoreContract
import app.mobify.orderbreadandroid.utils.repository.RepositoryContract
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPrefContract

class CheckoutPresenter : CheckoutContract.Presenter {
    lateinit var repository: RepositoryContract
    lateinit var sharedPref: SharedPrefContract
    lateinit var view: CheckoutContract.View
    lateinit var memoryStore: MemoryStoreContract

    private var wallet: Wallet = Wallet(mutableListOf())
    private lateinit var orderVerification: OrderVerification

    override fun loadData() {
        sharedPref.getCart().let {
            //gerar o resumo
            var resume = ""
            it.breads.forEach { item -> resume += view.getResumeString(item) }

            view.showResume(resume)

            //gerar o total a ser pago
            //dentro do carrinho
            view.showTotal(it.total)
        }

        //obter a localização
        view.getLocation({
            view.showErrorGeneric(it)
        }) { lat: Double, long: Double ->
            //obter se atende na localização do cliente
            //obter localizações para retirada
            //obter data do pedido
            //obter previsão de entrega
            repository.allowCustomerLocation(lat, long) { orderVerification: OrderVerification ->
                this.orderVerification = orderVerification
                if (orderVerification.allowLocation) {
                    view.showLocationsToGetOrder(orderVerification.addressesToGetOrder)

                    //obter os cartões salvos a shared
                    wallet = sharedPref.getWallet()
                    view.showWallet(wallet)

                    view.showOrderDate(orderVerification.orderDate)
                    view.showDateToGetOrder(orderVerification.dateToGetOrder)
                } else {
                    view.showErrorGeneric(orderVerification.errorLocation)
                }
            }
        }
    }

    override fun checkout() {
        //salvar no shared pref o pedido
        //salvar no backend o pedido
        //proceder o pagamento com a cielo
        //salvar no shared o pagamento
        //salvar pagamento aprovado no backend
    }
}