package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Unit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_UnitInfoHandler
{
    private val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api:APIs = retrofit.create(APIs::class.java)

    fun start(buildingId:Int , unitNumber:Int , callback:CallBack)
    {
        val call : Call<Unit> = api.unitInfo(buildingId , unitNumber)
        call.enqueue(object: Callback<Unit>
        {
            override fun onFailure(call: Call<Unit>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>)
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
        fun onResponse(unit:Unit)
    }
}