package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import ir.sinasoheili.building_manager.MODEL.Manager
import ir.sinasoheili.building_manager.MODEL.ManagerRegisterResponse
import ir.sinasoheili.building_manager.R
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class API_RegisterManagerHandler constructor(context:Context)
{
    val context : Context = context

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apis : APIs = retrofit.create(APIs::class.java)

    fun start(password:String , phone:String , callBack:callBack)
    {
        val call : Call<ManagerRegisterResponse> =  apis.managerRegister(password , phone)

        call.enqueue(object:Callback<ManagerRegisterResponse>
        {
            override fun onFailure(call: Call<ManagerRegisterResponse>, t: Throwable)
            {
                callBack.onFailure()
            }

            override fun onResponse(call: Call<ManagerRegisterResponse> , response: Response<ManagerRegisterResponse>)
            {
                callBack.onResponse(response)
            }
        })
    }


    interface callBack
    {
        fun onFailure()
        fun onResponse(response:Response<ManagerRegisterResponse>)
    }
}