package ir.sinasoheili.building_manager.MODEL

import java.io.Serializable

class Unit constructor(val owner_name:String , val phone:String , val unit_number:Int , val tag:Int , val building_id:Int) : Serializable
{
    override fun toString():String
    {
        return "owner_name:$owner_name | phone:$phone | unit_number:$unit_number | tag:$tag | bulding_id:$building_id"
    }
}