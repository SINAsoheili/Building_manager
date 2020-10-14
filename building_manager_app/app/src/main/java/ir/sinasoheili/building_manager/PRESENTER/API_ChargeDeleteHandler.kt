package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeDeleteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_ChargeDeleteHandler
{
    private val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api : APIs = retrofit.create(APIs::class.java)

    fun start(charge:Charge , callback:CallBack)
    {
        val call : Call<ChargeDeleteResponse> = api.chargeDelete(charge.id)
        call.enqueue(object: Callback<ChargeDeleteResponse>
        {
            override fun onFailure(call: Call<ChargeDeleteResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<ChargeDeleteResponse>, response: Response<ChargeDeleteResponse>)
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
        fun onResponse(response:ChargeDeleteResponse)
    }
}