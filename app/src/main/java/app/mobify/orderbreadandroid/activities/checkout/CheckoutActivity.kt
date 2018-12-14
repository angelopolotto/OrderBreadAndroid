package app.mobify.orderbreadandroid.activities.checkout

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import app.mobify.orderbreadandroid.R
import app.mobify.orderbreadandroid.activities.base.BaseActivity
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStore
import app.mobify.orderbreadandroid.utils.permissions.Permissions
import app.mobify.orderbreadandroid.utils.repository.Repository
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPref
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.android.inject

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
//            .withTitle("Permissão para acessar sua localização")
//            .withMessage("Permissão para acessar sua localização é necessária para analizarmos se possímos locais de retirada na sua cidade.")
            Permissions.requestLocationPermission(this)
        }

    }

    override fun onPause() {
        super.onPause()
        repository.disposable?.dispose()
    }

    override fun getLocation(location: (lat: Double, long: Double) -> Unit, error: (message: String) -> Unit) {
        if (Permissions.checkLocationPermission(this)) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    location?.let {
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
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.loadData()
        }
    }
}
