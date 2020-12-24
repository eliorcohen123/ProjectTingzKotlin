package elior.com.infrastructurekotlin.RetrofitPackage

import com.eliorcohen.projecttingzkotlin.ModelsPackage.TotalModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url
import java.util.*

interface GetDataService {

    @GET
    fun getAllData(
        @Header("Content-Type") token1: String,
        @Url url: String
    ): Observable<ArrayList<TotalModel>>

    companion object {
        fun create(): GetDataService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.androidhive.info")
                .build()

            return retrofit.create(GetDataService::class.java)
        }
    }

}
