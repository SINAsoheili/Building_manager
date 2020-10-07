package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.MODEL.NotificationAddResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_NotificationAddHandler
{
    val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api : APIs = retrofit.create(APIs::class.java)

    fun start(notification:Notification , buildingId:Int , callback:CallBack)
    {
        val call : Call<NotificationAddResponse> = api.notificationAdd(notification.text , notification.date , notification.title , buildingId)
        call.enqueue(object:Callback<NotificationAddResponse>
        {
            override fun onFailure(call: Call<NotificationAddResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<NotificationAddResponse> , response: Response<NotificationAddResponse>)
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
        fun onResponse(result:NotificationAddResponse)
    }
}