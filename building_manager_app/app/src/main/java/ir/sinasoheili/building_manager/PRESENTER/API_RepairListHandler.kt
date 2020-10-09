package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Repair
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_RepairListHandler
{
    private val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api : APIs = retrofit.create(APIs::class.java)

    fun start(buildingId:Int , callback:CallBack)
    {
        val call : Call<List<Repair>> = api.repairList(buildingId)
        call.enqueue(object:Callback<List<Repair>>
        {
            override fun onFailure(call: Call<List<Repair>>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Repair>>, response: Response<List<Repair>>)
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
        fun onResponse(repairList:List<Repair>)
    }
}