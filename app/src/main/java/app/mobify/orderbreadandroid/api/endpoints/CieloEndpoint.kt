package app.mobify.orderbreadandroid.api.endpoints

import app.mobify.orderbreadandroid.BuildConfig
import app.mobify.orderbreadandroid.api.models.cielo.CieloTransaction
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CieloEndpoint {

    //https://developercielo.github.io/manual/'?json#transa%C3%A7%C3%A3o-simples
    @Headers(
        "MerchantId: ${BuildConfig.MERCHANT_ID}",
        "MerchantKey: ${BuildConfig.MERCHANT_KEY}"
    )
    @POST("1/sales")
    fun paySimple(@Body transactionEndpoint: CieloTransaction): Observable<CieloTransaction>

    companion object {
        fun create(): CieloEndpoint {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(BuildConfig.CIELO_URL)
                .build()

            return retrofit.create(CieloEndpoint::class.java)
        }
    }
}