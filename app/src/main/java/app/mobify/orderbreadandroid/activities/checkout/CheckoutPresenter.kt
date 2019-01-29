package app.mobify.orderbreadandroid.activities.checkout

import app.mobify.orderbreadandroid.api.models.checkout.OrderVerification
import app.mobify.orderbreadandroid.api.models.checkout.ShippingType
import app.mobify.orderbreadandroid.api.models.cielo.CreditCard
import app.mobify.orderbreadandroid.api.models.user.Address
import app.mobify.orderbreadandroid.api.models.user.Cart
import app.mobify.orderbreadandroid.api.models.user.Order
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
    private lateinit var currentShippingType: ShippingType
    private lateinit var currentAddressToGetOrder: Address
    private lateinit var orderVerification: OrderVerification
    private lateinit var currentCreditCard: CreditCard
    private lateinit var shippingAddresses: List<Address>
    private lateinit var currentShippingAddress: Address
    private lateinit var cart: Cart


    override fun loadData() {
        cart = sharedPref.getCart()
        cart.let {
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
            //obter tipo de entrega
            //obter localizações para retirada
            //obter valor do frete
            //obter data do pedido
            //obter previsão de entrega
            repository.allowCustomerLocation(lat, long) { orderVerification: OrderVerification ->
                this.orderVerification = orderVerification
                if (orderVerification.allowLocation) {
                    setupShippingType()
                    setupAddressesGetOrder()
                    setupShippingAddress()
                    setupCreditCart()
                    view.showOrderDate(orderVerification.orderDate)
                    view.showDateToGetOrder(orderVerification.dateToGetOrder)
                } else {
                    view.showErrorGeneric(orderVerification.errorLocation)
                }
            }
        }
    }

    private fun setupShippingType() {
        orderVerification.shippingTypes.apply {
            forEach {
                if (it.default) {
                    currentShippingType = it
                    view.showShippingType(currentShippingType)
                }
            }
        }
    }

    private fun setupAddressesGetOrder() {
        orderVerification.addressesGetOrder.apply {
            forEach {
                if (it.default) {
                    currentAddressToGetOrder = it
                    view.showAddressGetOrder(currentAddressToGetOrder)
                }
            }
        }
    }

    private fun setupShippingAddress() {
        shippingAddresses = sharedPref.getUser()?.addressesShipping ?: mutableListOf()
        shippingAddresses.apply {
            forEach {
                if (it.default) {
                    currentShippingAddress = it
                }
            }
            if (isEmpty()) {
                view.showEmptyAddress()
            }
        }
    }

    private fun setupCreditCart() {
        //obter os cartões salvos a shared
        wallet = sharedPref.getWallet()
        //obtem o cartão atual selecionado
        wallet.creditCards.apply {
            forEach {
                if (it.default) {
                    currentCreditCard = it
                    view.showWallet(currentCreditCard)
                }
            }
            if (isEmpty()) {
                view.showEmptyWallet()
            }
        }
    }

    override fun showShippingTypes() {
        view.showShippingTypesDialog(currentShippingType, orderVerification.shippingTypes)
    }


    override fun showAddresses() {
        view.showShippingAddresses(currentShippingAddress, shippingAddresses)
    }

    override fun showPlacesGetOrder() {
        view.showLocationsToGetOrder(currentAddressToGetOrder, orderVerification.addressesGetOrder)
    }

    override fun showListCreditCard() {
        view.showWalletDialog(currentCreditCard, wallet)
    }

    override fun payOrder() {
        //cria um novo pedido
        val order = Order(
            orderVerification.orderNumber,
            cart.breads, cart.total, orderVerification.orderDate, currentShippingType
        )

        when (currentShippingType.type) {
            ShippingType.Type.DELIVERY -> order.addressShipping = currentShippingAddress
            ShippingType.Type.GET_ORDER -> order.addressGetOrder = currentAddressToGetOrder
        }

        //salvar no shared pref o pedido
        sharedPref.saveOrder(order)

        //salvar no backend o pedido
        repository.saveOrder(order) { saved ->
            if (saved) {
                //proceder o pagamento com a cielo
                repository.payment()

                //salvar no shared o pagamento
                //salvar pagamento aprovado no backend
            } else {
                view.showErrorSaveOrder()
            }
        }
    }
}