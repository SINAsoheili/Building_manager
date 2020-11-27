package ir.sinasoheili.building_manager.MODEL

class Building constructor(name:String , address:String , unit_count:Int)
{
    var id : Int = -1

    var name : String = name
    set(value)
    {
        if(! value.isEmpty())
        {
            field = value
        }
    }

    var address : String = address
    set(value)
    {
        if(! value.isEmpty())
        {
            field = value
        }
    }

    var unit_count : Int = unit_count
    set(value)
    {
        if(value > 0)
        {
            field = value
        }
    }

    override fun toString(): String
    {
        return "name:$name | address:$address | unit_count:$unit_count"
    }
}