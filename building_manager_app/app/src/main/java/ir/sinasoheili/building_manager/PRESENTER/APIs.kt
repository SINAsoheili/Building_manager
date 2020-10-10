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

    @GET("/notification/add")
    fun notificationAdd(@Query("text") text:String,@Query("title") title:String ,@Query("buildingId") buildingId:Int) : Call<NotificationAddResponse>

    @GET("/repair/add")
    fun repairAdd(@Query("date")date:String , @Query("comment")comment:String , @Query("title")title:String , @Query("amount")amount:Double , @Query("buildingId")buildingId:Int):Call<RepairAddResponse>

    @GET("/receipt/add")
    fun receiptAdd(@Query("receipt_type") receipt_type:Int ,@Query("pay_date") pay_date:String ,@Query("issue_date") issue_date:String ,@Query("amount") amount:Double ,@Query("id_receipt") id_receipt:String ,@Query("id_payment") id_payment:String ,@Query("building_id") building_id:Int):Call<ReceiptAddResponse>

    @GET("/notification/list")
    fun notificationList(@Query("buildingId") buildingId:Int):Call<List<Notification>>

    @GET("/repair/list")
    fun repairList(@Query("buildingId") buildingId:Int):Call<List<Repair>>

    @GET("/receipt/list")
    fun receiptList(@Query("buildingId") buildingId:Int):Call<List<Receipt>>

    @GET("/unit/del")
    fun unitDelete(@Query("phone") phone:String ,@Query("unit_number") unitNumber:Int , @Query("building_id") buildingId:Int):Call<UnitDeleteResponse>

    @GET("/charge/add")
    fun chargeAdd(@Query("amount") amount:Double , @Query("status")status:Int , @Query("issue_date")issueDate:String , @Query("pay_date")payDate:String , @Query("manager_id")managerId:Int , @Query("building_id")buildingId:Int , @Query("unit_number")unitNumber:Int):Call<ChargeAddResponse>

    @GET("/charge/list")
    fun chargeList(@Query("buildingId")buildingId:Int, @Query("unitNumber")unitNumber:Int):Call<List<Charge>>

    @GET("/repair/del")
    fun repairDelete(@Query("id")id:Int):Call<RepairDeleteResponse>

    @GET("/receipt/del")
    fun receiptDelete(@Query("id")id:Int):Call<ReceiptDeleteResponse>

    @GET("/notification/del")
    fun notificationDelete(@Query("id")id:Int):Call<NotificationDeleteResponse>
}