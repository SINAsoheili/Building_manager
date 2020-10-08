package ir.sinasoheili.building_manager.MODEL

class Notification constructor(text:String , title:String , building_id:Int)
{
    constructor(text:String , date:String , title:String , building_id:Int) : this(text , title , building_id)
    {
        this.date = date
    }

    var id : Int = -1
    get() = field
    set(value)
    {
        field = value
    }

    var text : String = text
    get() = field
    set(value)
    {
        field = value
    }

    var date : String? = null
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

    var building_id : Int = building_id
    get() = building_id
    set(value)
    {
        field = value
    }

    override fun toString():String
    {
        return "id:$id | text:$text | title:$title | building_id:$building_id"
    }
}