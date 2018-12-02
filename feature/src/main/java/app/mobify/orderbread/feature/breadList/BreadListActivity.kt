package app.mobify.orderbread.feature.breadList

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import app.mobify.orderbread.feature.R
import app.mobify.orderbread.feature.api.models.BreadItem
import app.mobify.orderbread.feature.base.BaseActivity
import app.mobify.orderbread.feature.repository.Repository
import kotlinx.android.synthetic.main.activity_bread_list.*
import org.koin.android.ext.android.inject

class BreadListActivity : BaseActivity(), BreadListContract.View {
    val repository: Repository by inject()
    val presenter: BreadListPresenter by inject()

    override fun showDetails(bread: BreadItem) {
//        startActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bread_list)
        rvBreads.layoutManager = GridLayoutManager(this, 2)
        progress = pbLoading
    }

    override fun onStart() {
        super.onStart()

        repository.base = this
        presenter.repository = repository
        presenter.view = this

        presenter.loadBreads()
    }

    override fun showBreads(breads: ArrayList<BreadItem>) {
        rvBreads.adapter = BreadListAdapter(this, breads) {
            presenter.showDetailsBreads(it)
        }
    }
}
