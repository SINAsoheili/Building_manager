package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.widget.Toast
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_UnitListHandler constructor(context: Context)
{
    private val context:Context = context

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api:APIs = retrofit.create(APIs::class.java)

    fun start(buildingId:Int , callback:CallBack)
    {
        val call:Call<List<Unit>> = api.unitList(buildingId)
        call.enqueue(object: Callback<List<Unit>>
        {
            override fun onFailure(call: Call<List<Unit>>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Unit>>, response: Response<List<Unit>>)
            {
                if(response.code() == 200)
                {
                    callback.onResponse(response.body()!!)
                }
            }

        })
    }

    interface CallBack
    {
        fun onFailure()
        fun onResponse(unitList:List<Unit>)
    }
}