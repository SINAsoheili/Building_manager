package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.widget.Toast
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_BuildingListHandler constructor(context: Context)
{
    private val context : Context = context

    private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(APIs.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api : APIs = retrofit.create(APIs::class.java)


    fun start(managerId:Int):List<Building>
    {
        var buildingList : List<Building> = listOf()

        val call : Call<List<Building>> = api.buildingList(managerId)
        call.enqueue(object: Callback<List<Building>>
        {
            override fun onFailure(call: Call<List<Building>>, t: Throwable)
            {
                Toast.makeText(context , context.getString(R.string.toast_fail_connect_to_server) , Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Building>> , response: Response<List<Building>>)
            {
                if(response.code() == 200)
                {
                    buildingList = response.body()!!
                }
            }
        })

        return buildingList
    }
}