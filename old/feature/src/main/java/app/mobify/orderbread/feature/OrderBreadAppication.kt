package app.mobify.orderbread.feature

import android.app.Application
import android.util.Log
import app.mobify.orderbread.feature.activities.breadDetails.BreadDetailsPresenter
import app.mobify.orderbread.feature.activities.breadList.BreadListPresenter
import app.mobify.orderbread.feature.activities.login.LoginPresenter
import app.mobify.orderbread.feature.utils.memoryStore.MemoryStore
import app.mobify.orderbread.feature.utils.repository.Repository
import app.mobify.orderbread.feature.utils.sharedPrefs.SharedPref
import com.google.firebase.crash.FirebaseCrash
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class OrderBreadAppication: Application() {
    val myModule = module {
        single { SharedPref() }
        single { MemoryStore() }
        single { Repository() }
        single { BreadListPresenter() }
        single { BreadDetailsPresenter() }
        single { LoginPresenter() }
    }

    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin(this, listOf(myModule))
    }
}