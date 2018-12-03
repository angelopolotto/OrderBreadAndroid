package app.mobify.orderbread.feature.activities.breadList

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import app.mobify.orderbread.feature.R
import app.mobify.orderbread.feature.api.models.BreadItem
import app.mobify.orderbread.feature.activities.base.BaseActivity
import app.mobify.orderbread.feature.activities.breadDetails.BreadDetailsActivity
import app.mobify.orderbread.feature.utils.`super`.MemStore
import app.mobify.orderbread.feature.utils.repository.Repository
import kotlinx.android.synthetic.main.activity_bread_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class BreadListActivity : BaseActivity(), BreadListContract.View {
    val repository: Repository by inject()
    val presenter: BreadListPresenter by inject()
    val memStore: MemStore by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bread_list)

        tvToolbarTitle.text = getString(R.string.bread_list_title)

        rvBreads.layoutManager = GridLayoutManager(this, 2)
        progress = pbLoading
    }

    override fun onStart() {
        super.onStart()

        repository.base = this
        presenter.repository = repository
        presenter.memStore = memStore
        presenter.view = this

        presenter.loadBreads()
    }

    override fun showBreads(breads: ArrayList<BreadItem>) {
        rvBreads.adapter = BreadListAdapter(this, breads) {
            presenter.showDetailsBreads(it)
        }
    }

    override fun showDetails() {
        startActivity(BreadDetailsActivity::class.java)
    }
}
