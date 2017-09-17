package com.goldendevloper.retrofitapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.goldendevloper.retrofitapp.model.IPAddressInfo
import com.goldendevloper.retrofitapp.uitls.AppRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppRetrofit.client().getIPAddressInfo().enqueue(object : Callback<IPAddressInfo> {
            override fun onResponse(call: Call<IPAddressInfo>, response: Response<IPAddressInfo>) {

                Log.e("Error", "code : " + response.code())
                Log.e("Error", "message : " + response.message())
                Log.e("Error", "body : " + response.body().toString())
                Log.e("Error", "raw : " + response.raw().toString())

                textView.text = response.body().toString()
            }

            override fun onFailure(call: Call<IPAddressInfo>, t: Throwable?) {
                Log.e("Error", "code : " + t.toString())
            }
        })
    }
}
