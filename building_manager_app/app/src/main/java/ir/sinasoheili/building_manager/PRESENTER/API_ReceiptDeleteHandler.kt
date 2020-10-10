package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.ReceiptDeleteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_ReceiptDeleteHandler
{
    private val retrofit:Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api : APIs = retrofit.create(APIs::class.java)

    fun start(id:Int , callback: CallBack)
    {
        val call: Call<ReceiptDeleteResponse> = api.receiptDelete(id)
        call.enqueue(object:Callback<ReceiptDeleteResponse>
        {
            override fun onFailure(call: Call<ReceiptDeleteResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<ReceiptDeleteResponse>, response: Response<ReceiptDeleteResponse>)
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
        fun onResponse(result:ReceiptDeleteResponse)
    }
}