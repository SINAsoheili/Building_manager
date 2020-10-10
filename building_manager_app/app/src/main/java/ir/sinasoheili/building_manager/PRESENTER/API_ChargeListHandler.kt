package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_ChargeListHandler
{
    val retrofit:Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api:APIs = retrofit.create(APIs::class.java)

    fun start(buildingId:Int , unitNumber:Int , callback:CallBack)
    {
        val call : Call<List<Charge>> = api.chargeList(buildingId , unitNumber)
        call.enqueue(object: Callback<List<Charge>>
        {
            override fun onFailure(call: Call<List<Charge>>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Charge>>, response: Response<List<Charge>>)
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
        fun onResponse(items:List<Charge>)
    }
}