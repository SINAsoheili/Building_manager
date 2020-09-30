package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.widget.Toast
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_UnitInfo constructor(context:Context)
{
    private val context:Context = context

    private val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api:APIs = retrofit.create(APIs::class.java)

    fun start(buildingId:Int , unitNumber:Int):Unit?
    {
        var unit:Unit? = null

        val call: Call<Unit> = api.unitInfo(buildingId , unitNumber)
        call.enqueue(object:Callback<Unit>
        {
            override fun onFailure(call: Call<Unit>, t: Throwable)
            {
                Toast.makeText(context , context.getString(R.string.toast_fail_connect_to_server) , Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>)
            {
                if(response.code() == 200)
                {
                    unit = response.body()
                }
            }

        })

        return unit
    }
}