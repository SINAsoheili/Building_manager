package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.NotificationDeleteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_NotificationDeleteHandler
{
    private val retrofit:Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api:APIs = retrofit.create(APIs::class.java)

    fun start(id:Int , callback:CallBack)
    {
        val call: Call<NotificationDeleteResponse> = api.notificationDelete(id)
        call.enqueue(object : Callback<NotificationDeleteResponse>
        {
            override fun onFailure(call: Call<NotificationDeleteResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<NotificationDeleteResponse>, response: Response<NotificationDeleteResponse>)
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
        fun onResponse(result:NotificationDeleteResponse)
    }
}