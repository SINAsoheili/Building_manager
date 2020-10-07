package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptAddResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_ReceiptAddHandler
{
    val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api : APIs = retrofit.create(APIs::class.java)

    fun start(receipt:Receipt , buildingId:Int , callback:CallBack)
    {
        val call:Call<ReceiptAddResponse> = api.receiptAdd(receipt.type , receipt.pay_date , receipt.issue_date , receipt.amount , receipt.id_receipt , receipt.id_payment , buildingId)
        call.enqueue(object:Callback<ReceiptAddResponse>
        {
            override fun onFailure(call: Call<ReceiptAddResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<ReceiptAddResponse>, response: Response<ReceiptAddResponse>)
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
        fun onResponse(result: ReceiptAddResponse)
    }
}