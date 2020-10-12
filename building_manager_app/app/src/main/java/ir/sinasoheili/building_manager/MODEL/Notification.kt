package ir.sinasoheili.building_manager.MODEL

class Notification constructor(val text:String , val title:String , val building_id:Int)
{
    var id : Int = -1
    var date : String? = null

    constructor(text:String , date:String , title:String , building_id:Int) : this(text , title , building_id)
    {
        this.date = date
    }

    override fun toString():String
    {
        return "id:$id | text:$text | title:$title | building_id:$building_id"
    }
}