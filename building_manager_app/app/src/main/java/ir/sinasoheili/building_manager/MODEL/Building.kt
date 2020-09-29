package ir.sinasoheili.building_manager.MODEL

class Building constructor(name:String , cash:Double=0.0 , address:String , unit_count:Int)
{
    var id : Int = -1
    get() = field
    set(value)
    {
        field = value
    }

    var name : String = name
    get() = field
    set(value)
    {
        if(! value.isEmpty())
        {
            field = value
        }
    }

    var cash : Double = cash
    get() = field
    set(value)
    {
        if(cash >= 0)
        {
            field = cash
        }
    }

    var address : String = address
    get() = field
    set(value)
    {
        if(! value.isEmpty())
        {
            field = value
        }
    }

    var unit_count : Int = unit_count
    get() = field
    set(value)
    {
        if(value > 0)
        {
            field = value
        }
    }

    override fun toString(): String
    {
        return "name:$name | cash:$cash | address:$address | unit_count:$unit_count"
    }
}