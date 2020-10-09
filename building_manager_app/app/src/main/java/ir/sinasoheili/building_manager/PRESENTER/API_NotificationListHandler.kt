package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Notification
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_NotificationListHandler
{
    val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api : APIs = retrofit.create(APIs::class.java)

    fun start(buildingId:Int , callback:CallBack)
    {
        val call : Call<List<Notification>> = api.notificationList(buildingId)
        call.enqueue(object : Callback<List<Notification>>
        {
            override fun onFailure(call: Call<List<Notification>>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Notification>>, response: Response<List<Notification>>)
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
        fun onResponse(notifList:List<Notification>)
    }
}