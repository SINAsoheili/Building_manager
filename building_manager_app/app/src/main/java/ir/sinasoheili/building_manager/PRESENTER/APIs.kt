package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.ManagerRegisterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs
{
    @GET("/manager/register")
    fun managerRegister(@Query("password") password:String , @Query("phone") phone:String):Call<ManagerRegisterResponse>
}