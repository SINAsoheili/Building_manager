package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.widget.Toast
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.MODEL.UnitAddResponse
import ir.sinasoheili.building_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_UnitAdd constructor(context:Context)
{
    private val context:Context = context

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(APIs.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val api:APIs = retrofit.create(APIs::class.java)

    fun start(unit:Unit , callBack:CallBack)
    {
        var call : Call<UnitAddResponse> = api.unitAdd(unit.owner_name , unit.phone , unit.unit_number , unit.tag , unit.building_id)
        call.enqueue(object: Callback<UnitAddResponse>
        {
            override fun onFailure(call: Call<UnitAddResponse>, t: Throwable)
            {
                callBack.onFailure()
//                Toast.makeText(context , context.getString(R.string.toast_fail_connect_to_server) , Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UnitAddResponse> , response: Response<UnitAddResponse>)
            {
                if(response.code() == 200)
                {
                    callBack.onResponse(response.body()!!)
                }
            }
        })
    }

    interface CallBack
    {
        fun onFailure()
        fun onResponse(response:UnitAddResponse)
    }
}