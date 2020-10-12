package ir.sinasoheili.building_manager.MODEL

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Repair constructor (date:String , comment:String , title:String , amount:Double , building_id:Int)
{
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

    var id : Int = -1
    get() = field
    set(value)
    {
        field = value
    }

    var date : String = date
    get() = field
    set(value)
    {
        field = value
    }

    var comment : String = comment
    get() = field
    set(value)
    {
        field = value
    }

    var title : String = title
    get() = field
    set(value)
    {
        field = value
    }

    var amount : Double = amount
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

    override fun toString():String
    {
        return "id:$id | date:$date | comment:$comment | title:$title | amount:$amount | building_id:$building_id"
    }
}