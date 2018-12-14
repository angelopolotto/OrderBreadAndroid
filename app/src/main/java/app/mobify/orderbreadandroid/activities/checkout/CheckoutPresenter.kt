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
        //gerar o resumo
        //gerar o total a ser pago
        //obter a localização
        //obter se atende na localização do cliente
        //obter os cartões salvos a shared
        //obter data do pedido
        //obter previsão de entrega
    }

    override fun checkout() {
        //salvar no shared pref o pedido
        //salvar no backend o pedido
        //proceder o pagamento com a cielo
        //salvar no shared o pagamento
        //salvar pagamento aprovado no backend
    }
}