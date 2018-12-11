package app.mobify.orderbread.feature.activities.breadDetails

import android.content.Intent
import android.os.Bundle
import app.mobify.orderbread.R.string
import app.mobify.orderbread.feature.R
import app.mobify.orderbread.feature.activities.base.BaseActivity
import app.mobify.orderbread.feature.activities.login.LoginActivity
import app.mobify.orderbread.feature.api.models.Bread
import app.mobify.orderbread.feature.utils.memoryStore.MemoryStore
import app.mobify.orderbread.feature.utils.repository.Repository
import app.mobify.orderbread.feature.utils.sharedPrefs.SharedPref
import app.mobify.orderbread.feature.utils.views.cutomStartActivityForResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_bread_details.*
import org.koin.android.ext.android.inject

class BreadDetailsActivity : BaseActivity(), BreadDetailsContract.View {

    override fun startLogin() {
        cutomStartActivityForResult<LoginActivity>(LoginActivity.rcSignIn)
    }

    override fun addedToCart() {
        onBackPressed()
    }

    private val repository: Repository by inject()
    private val presenter: BreadDetailsPresenter by inject()
    private val memoryStore: MemoryStore by inject()
    private val sharedPref: SharedPref by inject()

    override fun onStart() {
        super.onStart()
        repository.base = this
        sharedPref.activity = this
        presenter.repository = repository
        presenter.view = this
        presenter.memoryStore = memoryStore
        presenter.sharedPref = sharedPref

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

        bOrder.setOnClickListener {
            presenter.orderBread()
        }
    }

    override fun showDetails(bread: Bread) {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LoginActivity.rcSignIn) {
            presenter.orderBread()
        }
    }
}
