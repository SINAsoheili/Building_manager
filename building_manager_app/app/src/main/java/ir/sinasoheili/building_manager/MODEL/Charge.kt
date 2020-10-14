package ir.sinasoheili.building_manager.MODEL

import android.os.Build
import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Charge constructor (amount:Double , status: ChargeStatus = ChargeStatus.unpaid , val issue_date:String , val manager_id:Int , val building_id:Int , val unit_number:Int)
{
    var id:Int = -1

    var status : Int = status.ordinal

    var pay_date : String = ""

    var amount : Double = amount
        set(value)
        {
            if(value >= 0 )
            {
                field = amount
            }
        }

    constructor(amount:Double, status: ChargeStatus = ChargeStatus.unpaid, issue_date:String, pay_date:String, manager_id:Int, building_id:Int, unit_number:Int):this(amount , status , issue_date , manager_id , building_id , unit_number)
    {
        this.pay_date = pay_date
    }

    constructor(id:Int , amount:Double, status: ChargeStatus = ChargeStatus.unpaid, issue_date:String, manager_id:Int, building_id:Int, unit_number:Int):this(amount , status , issue_date , manager_id , building_id , unit_number)
    {
        this.id = id
    }

    constructor(id:Int , amount:Double, status: ChargeStatus = ChargeStatus.unpaid, issue_date:String, pay_date:String, manager_id:Int, building_id:Int, unit_number:Int):this(amount , status , issue_date , manager_id , building_id , unit_number)
    {
        this.pay_date = pay_date
        this.id = id
    }

    companion object
    {
        fun convertDate(date:String):String
        {
            val DateStr : String = "${date.split(" ").get(1)} ${date.split(" ").get(2)} ${date.split(" ").get(3)}"
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                val format : DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
                var localDat : LocalDate = LocalDate.parse(DateStr , format)
                return localDat.toString().replace("-" , "/")
            }

            return DateStr
        }
    }

    override fun toString(): String
    {
        return "id:$id | amount:$amount | status:$status | issue_date:$issue_date | manager_id:$manager_id | building_id:$building_id | unit_number:$unit_number | pay_date:$pay_date"
    }
}