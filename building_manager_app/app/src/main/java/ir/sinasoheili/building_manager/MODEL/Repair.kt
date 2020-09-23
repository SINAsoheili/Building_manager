package ir.sinasoheili.building_manager.MODEL

class Repair constructor (date:String , comment:String , title:String , amount:Double , building_id:Int)
{
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