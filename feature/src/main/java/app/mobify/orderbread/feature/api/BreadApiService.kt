package app.mobify.orderbread.feature.api

import app.mobify.orderbread.feature.BuildConfig.SERVER_URL
import app.mobify.orderbread.feature.api.models.BreadItem
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BreadApiService {
    @GET("/breads")
    fun breads(): Observable<ArrayList<BreadItem>>

    companion object {
        fun create(): BreadApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build()

            return retrofit.create(BreadApiService::class.java)
        }
    }
}