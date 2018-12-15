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
        val cart = sharedPref.getCart()

        cart?.let {
            //gerar o resumo
            var resume = ""
            it.breads.forEach { resume += "${it.quantity} x ${it.name} = R$ ${it.total}\n" }

            //gerar o total a ser pago
            val total = cart.total

        } ?: run {
            view.showCartError()
            return
        }

        //obter a localização
        view.getLocation({ lat: Double, long: Double ->
            //obter se atende na localização do cliente
            repository.allowCustomerLocation(lat, long) { allow: Boolean, error: String ->
                if (allow) {
                    //obter os cartões salvos a shared
                    //obter data do pedido
                    //obter previsão de entrega
                } else {
                    view.showError(error)
                }
            }
        }) {
            view.showGetLocationError()
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