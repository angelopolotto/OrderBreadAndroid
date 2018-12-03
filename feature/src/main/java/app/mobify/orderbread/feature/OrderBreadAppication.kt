package app.mobify.orderbread.feature

import android.app.Application
import app.mobify.orderbread.feature.activities.breadDetails.BreadDetailsPresenter
import app.mobify.orderbread.feature.activities.breadList.BreadListPresenter
import app.mobify.orderbread.feature.utils.`super`.MemStore
import app.mobify.orderbread.feature.utils.repository.Repository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class OrderBreadAppication: Application() {
    val myModule = module {
        single { MemStore() }
        single { Repository() }
        single { BreadListPresenter() }
        single { BreadDetailsPresenter() }
    }

    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin(this, listOf(myModule))
    }
}