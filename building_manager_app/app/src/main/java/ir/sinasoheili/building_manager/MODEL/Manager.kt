package ir.sinasoheili.building_manager.MODEL

class Manager constructor(val id : Int , val phone : String)
{
    override fun toString():String
    {
        return "id:$id | phone:$phone"
    }
}