package ir.sinasoheili.building_manager.PRESENTER

import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.MODEL.BuildingRegisterResponse
import ir.sinasoheili.building_manager.MODEL.ManagerRegisterResponse
import ir.sinasoheili.building_manager.MODEL.UnitAddResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs
{
    @GET("/manager/register")
    fun managerRegister(@Query("password") password:String , @Query("phone") phone:String):Call<ManagerRegisterResponse>

    @GET("/building/register")
    fun buildingRegister(@Query("name") name:String , @Query("cash") cash:Double , @Query("address") address:String , @Query("unit_count") unit_count:Int , @Query("manager_id") manager_id:Int):Call<BuildingRegisterResponse>

    @GET("/building/list")
    fun buildingList(@Query("manager_id") manager_id:Int):Call<List<Building>>

    @GET("/unit/add")
    fun unitAdd(@Query("owner_name") owner_name:String , @Query("phone") phone:String , @Query("unit_number") unit_number:String , @Query("tag") tag:String , @Query("building_id") building_id:String):Call<UnitAddResponse>

    @GET("/unit/list")
    fun unitList(@Query("building_id") building_id:Int):Call<List<Unit>>
}