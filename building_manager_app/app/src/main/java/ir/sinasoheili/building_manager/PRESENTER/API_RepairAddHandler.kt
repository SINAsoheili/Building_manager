package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.MODEL.RepairAddResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_RepairAddHandler
{
    val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api : APIs = retrofit.create(APIs::class.java)

    fun start(repair:Repair , buildingId:Int , callback:CallBack)
    {
        val call : Call<RepairAddResponse> = api.repairAdd(repair.date , repair.comment , repair.title , repair.amount , buildingId)
        call.enqueue(object:Callback<RepairAddResponse>
        {
            override fun onFailure(call: Call<RepairAddResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<RepairAddResponse>, response: Response<RepairAddResponse>)
            {
                callback.onResponse(response.body()!!)
            }

        })
    }

    interface CallBack
    {
        fun onFailure()
        fun onResponse(result: RepairAddResponse)
    }
}