package ir.sinasoheili.building_manager.MODEL

class Manager constructor(id : Int , phone : String)
{
    var id : Int = id
    get() = field
    set(value)
    {
        field = value
    }

    var phone : String = phone
    get() = field
    set(value)
    {
        field = value
    }

    override fun toString():String
    {
        return "id:$id | phone:$phone"
    }
}