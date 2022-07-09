package xyz.heydarrn.githubuserwithapi

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIConfig {
    companion object{
        private const val BASE_URL="https://api.github.com/"
        fun getApiService() : APIService {
            val loggingInterceptor=if (BuildConfig.DEBUG){
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }else{
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            val client= OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(APIService::class.java)
        }
    }
}