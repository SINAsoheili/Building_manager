package ir.sinasoheili.building_manager.MODEL

import android.os.Build
import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Charge constructor (amount:Double, status: ChargeStatus = ChargeStatus.unpaid, issue_date:String, manager_id:Int, building_id:Int, unit_number:Int)
{
    constructor(amount:Double, status: ChargeStatus = ChargeStatus.unpaid, issue_date:String, pay_date:String, manager_id:Int, building_id:Int, unit_number:Int):this(amount , status , issue_date , manager_id , building_id , unit_number)
    {
        this.pay_date = pay_date
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

    var id:Int = -1
    get() = field
    set(value)
    {
        field = value
    }

    var amount : Double = amount
    get() = field
    set(value)
    {
        if(value >= 0 )
        {
            field = amount
        }
    }

    var status : Int = status.ordinal
    get() = field
    set(value)
    {
        field = value
    }

    var issue_date : String = issue_date
    get() = field
    set(value)
    {
        field = value
    }

    var manager_id : Int = manager_id
    get() = field
    set(value)
    {
        field = value
    }

    var building_id : Int = building_id
    get() = field
    set(value)
    {
        field = value
    }

    var unit_number : Int = unit_number
    get() = field
    set(value)
    {
        field = value
    }

    var pay_date : String = ""
    get() = field
    set(value)
    {
        field = value
    }

    override fun toString(): String
    {
        return "id:$id | amount:$amount | status:$status | issue_date:$issue_date | manager_id:$manager_id | building_id:$building_id | unit_number:$unit_number | pay_date:$pay_date"
    }
}