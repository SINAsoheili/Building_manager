package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.widget.Toast
import ir.sinasoheili.building_manager.MODEL.UnitDeleteResponse
import ir.sinasoheili.building_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API_DeleteUnitHandler constructor(context: Context)
{
    private val context:Context = context

    private val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    private val api:APIs = retrofit.create(APIs::class.java)

    fun start(buildingId:Int , unitNumber:Int):Boolean
    {
        var result:Boolean = false

        val call: Call<UnitDeleteResponse> = api.unitDelete(buildingId , unitNumber)

        call.enqueue(object:Callback<UnitDeleteResponse>
        {
            override fun onFailure(call: Call<UnitDeleteResponse>, t: Throwable)
            {
                Toast.makeText(context , context.getString(R.string.toast_fail_connect_to_server) , Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UnitDeleteResponse>, response: Response<UnitDeleteResponse>)
            {
                if(response.code() == 200)
                {
                    result = response.body()!!.status
                }
            }

        })

        return result
    }
}