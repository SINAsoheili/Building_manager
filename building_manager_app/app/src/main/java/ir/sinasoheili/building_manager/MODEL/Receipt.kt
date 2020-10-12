package ir.sinasoheili.building_manager.MODEL

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Receipt constructor(type:ReceiptType , val pay_date:String , val issue_date:String , val amount:Double , val id_receipt:String , val id_payment:String , val building_id:Int)
{
    var id : Int = -1
    var type : Int = type.ordinal

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

    override fun toString():String
    {
        return "id:$id | type:$type | pay_date:$pay_date | issue_date:$issue_date | amount:$amount | id_receipt:$id_receipt | id_payment:$id_payment | building_id:$building_id"
    }
}