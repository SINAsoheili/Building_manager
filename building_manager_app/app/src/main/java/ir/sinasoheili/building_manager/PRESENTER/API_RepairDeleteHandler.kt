package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.RepairDeleteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_RepairDeleteHandler
{
    val retrofit:Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api :APIs = retrofit.create(APIs::class.java)

    fun start(id:Int , callback:CallBack)
    {
        val call: Call<RepairDeleteResponse> = api.repairDelete(id)
        call.enqueue(object:Callback<RepairDeleteResponse>
        {
            override fun onFailure(call: Call<RepairDeleteResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<RepairDeleteResponse>, response: Response<RepairDeleteResponse>)
            {
                if(response.code() == 200 )
                {
                    callback.onResponse(response.body()!!)
                }
            }
        })
    }

    interface CallBack
    {
        fun onFailure()
        fun onResponse(result:RepairDeleteResponse)
    }
}