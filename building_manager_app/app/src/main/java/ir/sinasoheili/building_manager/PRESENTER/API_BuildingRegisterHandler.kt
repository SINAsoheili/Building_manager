package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.widget.Toast
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.MODEL.BuildingRegisterResponse
import ir.sinasoheili.building_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_BuildingRegisterHandler constructor(context:Context)
{
    private val context : Context = context

    private val retrofit : Retrofit = Retrofit.Builder()
                                              .baseUrl(APIs.BASE_URL)
                                              .addConverterFactory(GsonConverterFactory.create())
                                              .build()

    private val api : APIs = retrofit.create(APIs::class.java)

    fun start(building:Building, manager_id:Int , callback:callBack)
    {
        val call : Call<BuildingRegisterResponse> = api.buildingRegister(building.name , building.address , building.unit_count , manager_id)
        call.enqueue(object:Callback<BuildingRegisterResponse>
        {
            override fun onFailure(call: Call<BuildingRegisterResponse>, t: Throwable)
            {
                callback.onFailure()
            }

            override fun onResponse(call: Call<BuildingRegisterResponse> , response: Response<BuildingRegisterResponse>)
            {
                if(response.code() == 200)
                {
                    callback.onResponse(response.body()!!)
                }
            }
        })
    }

    interface callBack
    {
        fun onFailure()
        fun onResponse(response:BuildingRegisterResponse)
    }
}