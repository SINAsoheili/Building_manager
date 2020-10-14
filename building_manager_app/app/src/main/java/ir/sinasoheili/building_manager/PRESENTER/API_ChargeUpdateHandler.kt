package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeUpdateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_ChargeUpdateHandler
{
    private val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api : APIs = retrofit.create(APIs::class.java)

    fun start(charge: Charge , callBack:CallBack)
    {
        val call : Call<ChargeUpdateResponse> = api.chargeUpdate(charge.id , charge.amount , charge.status , charge.issue_date , charge.pay_date)
        call.enqueue(object: Callback<ChargeUpdateResponse>
        {
            override fun onFailure(call: Call<ChargeUpdateResponse>, t: Throwable)
            {
                callBack.onFailure()
            }

            override fun onResponse(call: Call<ChargeUpdateResponse>, response: Response<ChargeUpdateResponse>)
            {
                if(response.code() == 200)
                {
                    callBack.onResponse(response.body()!!)
                }
            }

        })
    }

    interface CallBack
    {
        fun onFailure()
        fun onResponse(response:ChargeUpdateResponse)
    }
}