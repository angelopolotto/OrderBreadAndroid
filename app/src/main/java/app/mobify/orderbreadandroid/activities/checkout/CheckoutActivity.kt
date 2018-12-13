package app.mobify.orderbreadandroid.activities.checkout

import android.os.Bundle
import app.mobify.orderbreadandroid.R
import app.mobify.orderbreadandroid.activities.base.BaseActivity
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStore
import app.mobify.orderbreadandroid.utils.repository.Repository
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPref
import org.koin.android.ext.android.inject

class CheckoutActivity : BaseActivity(), CheckoutContract.View {
    private val repository: Repository by inject()
    private val presenter: CheckoutPresenter by inject()
    private val sharedPref: SharedPref by inject()
    private val memoryStore: MemoryStore by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()

        repository.base = this
        presenter.repository = repository
        presenter.sharedPref = sharedPref
        presenter.memoryStore = memoryStore
        presenter.view = this

        presenter.loadData()
    }

    override fun onPause() {
        super.onPause()
        repository.disposable?.dispose()
    }
}
