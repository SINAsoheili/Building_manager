package ir.sinasoheili.building_manager.MODEL

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Notification constructor(val text:String , val title:String , val building_id:Int)
{
    var id : Int = -1
    var date : String? = null

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

    constructor(text:String , date:String , title:String , building_id:Int) : this(text , title , building_id)
    {
        this.date = date
    }

    override fun toString():String
    {
        return "id:$id | text:$text | title:$title | building_id:$building_id"
    }
}