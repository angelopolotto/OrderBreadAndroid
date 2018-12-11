package app.mobify.orderbreadandroid.activities.breadList

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import app.mobify.orderbreadandroid.activities.base.BaseActivity
import app.mobify.orderbreadandroid.activities.breadDetails.BreadDetailsActivity
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStore
import app.mobify.orderbreadandroid.utils.repository.Repository
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPref
import app.mobify.orderbreadandroid.R
import kotlinx.android.synthetic.main.activity_bread_list.*
import org.koin.android.ext.android.inject

class BreadListActivity : BaseActivity(), BreadListContract.View {
    private val repository: Repository by inject()
    private val presenter: BreadListPresenter by inject()
    private val memoryStore: MemoryStore by inject()
    private val sharedPref: SharedPref by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bread_list)
        actionBar.title = getString(R.string.bread_list_title)

        rvBreads.layoutManager = GridLayoutManager(this, 2)
        progress = pbLoading
    }

    override fun onStart() {
        super.onStart()

        repository.base = this
        sharedPref.activity = this
        presenter.repository = repository
        presenter.memoryStore = memoryStore
        presenter.sharedPref = sharedPref
        presenter.view = this

        presenter.loadData()
    }

    override fun onPause() {
        super.onPause()
        repository.disposable?.dispose()
    }

    override fun showBreads(breads: ArrayList<Bread>) {
        rvBreads.adapter = BreadListAdapter(this, breads) {
            presenter.showDetailsBreads(it)
        }
    }

    override fun showDetails() {
        startActivity(BreadDetailsActivity::class.java)
    }

    override fun showCart(total: Int) {

    }
}
