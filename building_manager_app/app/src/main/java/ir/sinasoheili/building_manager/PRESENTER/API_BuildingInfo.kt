package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.MODEL.Repair
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_BuildingInfo
{
    private val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api : APIs = retrofit.create(APIs::class.java)

    fun start(buildingId:Int , callback:CallBack)
    {
        val call : Call<List<Building>> = api.buildingInfo(buildingId)
        call.enqueue(object:Callback<List<Building>>
        {
            override fun onFailure(call: Call<List<Building>>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Building>>, response: Response<List<Building>>)
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
        fun onResponse(buildingList:List<Building>)
    }
}