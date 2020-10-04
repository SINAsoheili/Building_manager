package ir.sinasoheili.building_manager.MODEL

import java.io.Serializable

class Unit constructor(owner_name:String , phone:String , unit_number:Int , tag:Int , building_id:Int) : Serializable
{
    var owner_name : String = owner_name
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

    var unit_number : Int = unit_number
    get() = field
    set(value)
    {
        field = value
    }

    var tag : Int =  tag
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
        return "owner_name:$owner_name | phone:$phone | unit_number:$unit_number | tag:$tag | bulding_id:$building_id"
    }
}