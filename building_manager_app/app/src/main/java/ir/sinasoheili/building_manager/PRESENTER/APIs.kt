package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.*
import ir.sinasoheili.building_manager.MODEL.Unit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs
{
    companion object
    {
        val BASE_URL : String = "http://10.0.2.2:5000"
    }

    @GET("/manager/register")
    fun managerRegister(@Query("password") password:String , @Query("phone") phone:String):Call<ManagerRegisterResponse>

    @GET("/building/register")
    fun buildingRegister(@Query("name") name:String , @Query("cash") cash:Double , @Query("address") address:String , @Query("unit_count") unit_count:Int , @Query("manager_id") manager_id:Int):Call<BuildingRegisterResponse>

    @GET("/building/list")
    fun buildingList(@Query("manager_id") manager_id:Int):Call<List<Building>>

    @GET("/unit/add")
    fun unitAdd(@Query("owner_name") owner_name:String , @Query("phone") phone:String , @Query("unit_number") unit_number:Int , @Query("tag") tag:Int , @Query("building_id") building_id:Int):Call<UnitAddResponse>

    @GET("/unit/list")
    fun unitList(@Query("building_id") building_id:Int):Call<List<Unit>>

    @GET("/unit/info")
    fun unitInfo(@Query("building_id") building_id:Int , @Query("unit_number") unit_number:Int):Call<Unit>

    @GET("/unit/delete")
    fun unitDelete(@Query("building_id") building_id:Int , @Query("unit_number") unit_number:Int):Call<UnitDeleteResponse>

    @GET("/notification/add")
    fun notificationAdd(text:String , date:String , title:String , buildingId:Int) : Call<NotificationAddResponse>
}