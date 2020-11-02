package ir.sinasoheili.building_manager.MODEL

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Notification constructor(val title:String , val text:String , val date:String , val building_id:Int)
{
    var id : Int = -1

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
        return "id:$id | text:$text | title:$title | building_id:$building_id"
    }
}