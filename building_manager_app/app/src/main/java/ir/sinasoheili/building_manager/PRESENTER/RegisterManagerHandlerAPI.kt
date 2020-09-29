package ir.sinasoheili.building_manager.PRESENTER

import android.content.Context
import android.widget.Toast
import ir.sinasoheili.building_manager.MODEL.Manager
import ir.sinasoheili.building_manager.MODEL.ManagerRegisterResponse
import ir.sinasoheili.building_manager.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterManagerHandlerAPI constructor(context:Context) : Callback<ManagerRegisterResponse>
{
    val context : Context = context

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(APIs.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apis : APIs = retrofit.create(APIs::class.java)

    fun start(password:String , phone:String)
    {
        val call : Call<ManagerRegisterResponse> =  apis.managerRegister(password , phone)
        call.enqueue(this)
    }

    override fun onFailure(call: Call<ManagerRegisterResponse>, t: Throwable)
    {
        Toast.makeText(context , context.getString(R.string.toast_fail_connect_to_server) , Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<ManagerRegisterResponse> , response: Response<ManagerRegisterResponse>)
    {
        if(response.code() == 200)
        {
            if(response.body()!!.status == true)
            {
                val id : Int = response.body()!!.manager_id
                if(id != -1)
                {
                    FilePreferenceHandler.writeToFile(context , Manager.KEY_MANAGER_ID , id.toString())
                }
            }
            else
            {
                Toast.makeText(context , context.getString(R.string.toast_register_server_error), Toast.LENGTH_SHORT).show()
            }
        }
    }
}