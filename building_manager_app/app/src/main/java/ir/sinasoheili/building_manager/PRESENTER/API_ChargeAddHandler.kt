package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Charge
import ir.sinasoheili.building_manager.MODEL.ChargeAddResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_ChargeAddHandler
{
    val retrofit:Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api:APIs = retrofit.create(APIs::class.java)

    fun start(charge: Charge , callback:CallBack)
    {
        val call : Call<ChargeAddResponse> = api.chargeAdd(charge.amount , charge.status , charge.issue_date , charge.pay_date , charge.manager_id , charge.building_id , charge.unit_number )
        call.enqueue(object: Callback<ChargeAddResponse>
        {
            override fun onFailure(call: Call<ChargeAddResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<ChargeAddResponse>, response: Response<ChargeAddResponse>)
            {
                callback.onResponse(response.body()!!)
            }
        })
    }

    interface CallBack
    {
        fun onFailure()
        fun onResponse(result:ChargeAddResponse)
    }
}