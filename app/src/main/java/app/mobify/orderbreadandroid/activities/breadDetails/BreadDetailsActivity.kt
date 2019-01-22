package app.mobify.orderbreadandroid.activities.breadDetails

import android.content.Intent
import android.os.Bundle
import app.mobify.orderbreadandroid.R
import app.mobify.orderbreadandroid.activities.base.BaseActivity
import app.mobify.orderbreadandroid.activities.login.LoginActivity
import app.mobify.orderbreadandroid.api.models.Bread
import app.mobify.orderbreadandroid.utils.memoryStore.MemoryStore
import app.mobify.orderbreadandroid.utils.repository.Repository
import app.mobify.orderbreadandroid.utils.sharedPrefs.SharedPref
import app.mobify.orderbreadandroid.utils.views.cutomStartActivityForResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_bread_details.*
import org.koin.android.ext.android.inject

class BreadDetailsActivity : BaseActivity(), BreadDetailsContract.View {
    override fun errorMaxPerItem(maxPerItem: Int) {
        showError(getString(R.string.details_error_max_per_item, maxPerItem))
    }

    override fun errorMaxItemsCart(maxItemsCart: Int) {
        showError(getString(R.string.details_error_max_tems_cart, maxItemsCart))

    }

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
        presenter.repository = repository
        presenter.view = this
        presenter.memoryStore = memoryStore
        presenter.sharedPref = sharedPref

        presenter.loadDetails()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bread_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bOrder.setOnClickListener {
            presenter.orderBread()
        }
    }

    override fun showDetails(bread: Bread) {
        supportActionBar?.title = bread.name
        cdIndicator.configureViewPager(vpImages, BreadDetailsPagerAdapter(this, bread.images))
        tvName.text = bread.name
        tvPrice.text = resources.getString(R.string.details_price_value, bread.price)
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
            presenter.orderBread(true)
        }
    }
}
