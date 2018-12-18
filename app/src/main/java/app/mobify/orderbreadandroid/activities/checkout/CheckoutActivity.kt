package app.mobify.orderbreadandroid.activities.checkout

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import app.mobify.orderbreadandroid.R
import app.mobify.orderbreadandroid.activities.base.BaseActivity
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.api.models.user.Address
import app.mobify.orderbreadandroid.api.models.user.Wallet
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStore
import app.mobify.orderbreadandroid.utils.permissions.Permissions
import app.mobify.orderbreadandroid.utils.repository.Repository
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPref
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.cart_item.*
import org.koin.android.ext.android.inject
import java.math.BigDecimal
import java.util.*

class CheckoutActivity : BaseActivity(), CheckoutContract.View {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val repository: Repository by inject()
    private val presenter: CheckoutPresenter by inject()
    private val sharedPref: SharedPref by inject()
    private val memoryStore: MemoryStore by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        actionBar.setDisplayHomeAsUpEnabled(true)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStart() {
        super.onStart()

        repository.base = this
        presenter.repository = repository
        presenter.sharedPref = sharedPref
        presenter.memoryStore = memoryStore
        presenter.view = this

        if (Permissions.checkLocationPermission(this)) {
            presenter.loadData()
        } else {
            showInfo(
                getString(R.string.checkout_permission_title),
                getString(R.string.checkout_permission_message)
            ) { Permissions.requestLocationPermission(this) }
        }

    }

    override fun onPause() {
        super.onPause()
        repository.disposable?.dispose()
    }

    override fun getLocation(error: (message: String) -> Unit, location: (lat: Double, long: Double) -> Unit) {
        if (Permissions.checkLocationPermission(this)) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { locationDevice: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    locationDevice?.let {
                        location(it.latitude, it.longitude)
                    } ?: run {
                        error(getString(R.string.checkout_get_location_error))
                    }
                }
        } else {
            error(getString(R.string.checkout_get_location_permission_denied))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.loadData()
        }
    }

    override fun showResume(resume: String) {
        tvResume.text = resume
    }

    override fun showWallet(wallet: Wallet) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTotal(total: BigDecimal) {
        tvTotalPrice.text = getString(R.string.checkout_price_total_value, total)
    }

    override fun showLocationsToGetOrder(addressesToGetOrder: List<Address>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showOrderDate(orderDate: Date) {
        tvOrderDate.text = orderDate.toString()
    }

    override fun showDateToGetOrder(dateToGetOrder: Date) {
        tvDateToGetOrder.text = dateToGetOrder.toString()
    }

    override fun getResumeString(item: Bread): String {
        return resources.getString(
            R.string.checkout_resume_item,
            item.quantity.toString(), item.name, item.total
        )
    }

    override fun showErrorGeneric(error: String) {
        showError(error) {
            onBackPressed()
        }
    }
}
