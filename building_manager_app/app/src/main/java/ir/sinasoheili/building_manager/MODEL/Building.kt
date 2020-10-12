package ir.sinasoheili.building_manager.MODEL

class Building constructor(name:String , cash:Double=0.0 , address:String , unit_count:Int)
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

    var cash : Double = cash
    set(value)
    {
        if(cash >= 0)
        {
            field = cash
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
        return "name:$name | cash:$cash | address:$address | unit_count:$unit_count"
    }
}