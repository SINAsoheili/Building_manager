package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.MODEL.UnitDeleteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_UnitDeleteHandler
{
    val retrofit:Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api:APIs = retrofit.create(APIs::class.java)

    fun start(unit:Unit , callback:CallBack)
    {
        val call: Call<UnitDeleteResponse> = api.unitDelete(unit.phone , unit.unit_number , unit.building_id)
        call.enqueue(object: Callback<UnitDeleteResponse>
        {
            override fun onFailure(call: Call<UnitDeleteResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<UnitDeleteResponse>, response: Response<UnitDeleteResponse>)
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
        fun onResponse(response:UnitDeleteResponse)
    }
}