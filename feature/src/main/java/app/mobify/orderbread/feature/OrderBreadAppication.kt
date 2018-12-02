package app.mobify.orderbread.feature

import android.app.Application
import app.mobify.orderbread.feature.breadList.BreadListPresenter
import app.mobify.orderbread.feature.repository.Repository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class OrderBreadAppication: Application() {
    val myModule = module {
        single { Repository() }
        single { BreadListPresenter() }
    }

    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin(this, listOf(myModule))
    }
}