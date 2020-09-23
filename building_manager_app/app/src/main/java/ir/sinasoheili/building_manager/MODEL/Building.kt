package ir.sinasoheili.building_manager.MODEL

class Building constructor(name:String , cash:Double=0.0 , address:String , unit_count:Int)
{
    private var id : Int = -1
    get() = field
    set(value)
    {
        field = value
    }

    private var name : String = name
    get() = field
    set(value)
    {
        if(! value.isEmpty())
        {
            field = value
        }
    }

    private var cash : Double = cash
    get() = field
    set(value)
    {
        if(cash >= 0)
        {
            field = cash
        }
    }

    private var address : String = address
    get() = field
    set(value)
    {
        if(! value.isEmpty())
        {
            field = value
        }
    }

    private var unit_count : Int = unit_count
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