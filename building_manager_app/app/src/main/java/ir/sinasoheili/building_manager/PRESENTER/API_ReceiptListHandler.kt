package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Receipt
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_ReceiptListHandler
{
    private val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api : APIs = retrofit.create(APIs::class.java)

    fun start(buildingId : Int , callback:CallBack)
    {
        val call : Call<List<Receipt>> = api.receiptList(buildingId)
        call.enqueue(object: Callback<List<Receipt>>
        {
            override fun onFailure(call: Call<List<Receipt>>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Receipt>>, response: Response<List<Receipt>>)
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
        fun onResponse(receiptList:List<Receipt>)
    }
}