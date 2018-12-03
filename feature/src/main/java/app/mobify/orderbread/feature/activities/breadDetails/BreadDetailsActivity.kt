package app.mobify.orderbread.feature.activities.breadDetails

import android.os.Bundle
import android.view.View
import app.mobify.orderbread.feature.R
import app.mobify.orderbread.feature.activities.base.BaseActivity
import app.mobify.orderbread.feature.api.models.BreadItem
import app.mobify.orderbread.feature.utils.`super`.MemStore
import app.mobify.orderbread.feature.utils.repository.Repository
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject

class BreadDetailsActivity : BaseActivity(), BreadDetailsContract.View {
    val repository: Repository by inject()
    val presenter: BreadDetailsPresenter by inject()
    val memStore: MemStore by inject()

    override fun onStart() {
        super.onStart()

        repository.base = this
        presenter.repository = repository
        presenter.view = this
        presenter.memStore = memStore

        presenter.loadDetails()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bread_details)

        ibBack.visibility = View.VISIBLE
        ibBack.setOnClickListener { onBackPressed() }
    }

    override fun showDetails(bread: BreadItem) {
        tvToolbarTitle.text = bread.name
    }
}
