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

class BuildingRegisterHandlerAPI constructor(context:Context)
{
    private val context : Context = context

    private val retrofit : Retrofit = Retrofit.Builder()
                                              .baseUrl(APIs.BASE_URL)
                                              .addConverterFactory(GsonConverterFactory.create())
                                              .build()

    private val api : APIs = retrofit.create(APIs::class.java)

    fun start(building:Building, manager_id:Int):Int
    {
        var id : Int = -1

        val cal : Call<BuildingRegisterResponse> = api.buildingRegister(building.name , building.cash , building.address , building.unit_count , manager_id)
        cal.enqueue(object:Callback<BuildingRegisterResponse>
        {
            override fun onFailure(call: Call<BuildingRegisterResponse>, t: Throwable)
            {
                Toast.makeText(context , context.getString(R.string.toast_fail_connect_to_server) , Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<BuildingRegisterResponse> , response: Response<BuildingRegisterResponse>)
            {
                if(response.code() == 200)
                {
                    if(response.body()!!.status == true)
                    {

                        if(response.body()!!.id != -1)
                        {
                            id = response.body()!!.id
                        }
                    }
                    else
                    {
                        Toast.makeText(context , context.getString(R.string.toast_register_server_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        return id
    }
}