package app.mobify.orderbreadandroid.api.endpoints

import app.mobify.orderbreadandroid.BuildConfig
import app.mobify.orderbreadandroid.api.models.Bread
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface OrderBreadEndpoint {
    @GET("/breads")
    fun breads(): Observable<ArrayList<Bread>>

    companion object {
        fun create(): OrderBreadEndpoint {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BuildConfig.SERVER_URL)
                .build()

            return retrofit.create(OrderBreadEndpoint::class.java)
        }
    }
}