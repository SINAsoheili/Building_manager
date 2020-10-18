package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.UserAuthenticateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_UserAuthenticate
{
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api : APIs = retrofit.create(APIs::class.java)

    fun start(phone:String , buildingId:Int , callback:CallBack)
    {
        val call : Call<UserAuthenticateResponse> = api.userAuthenticate(buildingId , phone)
        call.enqueue(object:Callback<UserAuthenticateResponse>
        {
            override fun onFailure(call: Call<UserAuthenticateResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<UserAuthenticateResponse>, response: Response<UserAuthenticateResponse>)
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
        fun onResponse(result:UserAuthenticateResponse)
    }
}