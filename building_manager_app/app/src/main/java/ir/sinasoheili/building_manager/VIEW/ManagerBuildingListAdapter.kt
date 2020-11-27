package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Building
import ir.sinasoheili.building_manager.R

class ManagerBuildingListAdapter constructor(context:Context, buildingList:List<Building>) : ArrayAdapter<Building>(context , R.layout.building_list_item , buildingList)
{
    val buildingList : List<Building> = buildingList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var convertView : View? = convertView
        var viewHolder : ViewHolder? = null

        if(convertView == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.building_list_item , parent , false)
            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        }
        else
        {
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.fill(buildingList.get(position))
        return convertView!!
    }

    class ViewHolder constructor(view:View)
    {
        val context     : Context  = view.context
        val tvName      : TextView = view.findViewById(R.id.tv_buildingListItem_name)
        val tvCash      : TextView = view.findViewById(R.id.tv_buildingListItem_cash)
        val tvUnitCount : TextView = view.findViewById(R.id.tv_buildingListItem_unitCount)

        fun fill(building:Building)
        {
            tvName!!.text       = building.name
            tvUnitCount!!.text  = context.getString(R.string.unit_count)+" : "+building.unit_count.toString()
        }
    }
}