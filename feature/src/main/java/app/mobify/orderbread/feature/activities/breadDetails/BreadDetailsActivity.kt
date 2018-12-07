package app.mobify.orderbread.feature.activities.breadDetails

import android.os.Bundle
import app.mobify.orderbread.R.string
import app.mobify.orderbread.feature.R
import app.mobify.orderbread.feature.activities.base.BaseActivity
import app.mobify.orderbread.feature.api.models.BreadItem
import app.mobify.orderbread.feature.utils.`super`.MemStore
import app.mobify.orderbread.feature.utils.repository.Repository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_bread_details.*
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

        actionBar.setDisplayHomeAsUpEnabled(true)

        presenter.loadDetails()
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bread_details)
    }

    override fun showDetails(bread: BreadItem) {
        actionBar.title = bread.name
        cdIndicator.configureViewPager(vpImages, BreadDetailsPagerAdapter(this, bread.images))
        tvName.text = bread.name
        tvPrice.text = resources.getString(string.details_price_value, bread.price)
        tvDemensions.text = bread.dimensions
        tvDescription.text = bread.description
        tvCombinations.text = bread.combinations
        tvFlavor.text = bread.flavor
        tvDurability.text = bread.durability
        tvIngredients.text = bread.ingredients
        tvAllergic.text = bread.allergic
        Glide.with(this).load(bread.nutritional).into(ivNutritional)
    }
}
