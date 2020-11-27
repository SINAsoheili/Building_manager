package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Unit
import ir.sinasoheili.building_manager.R

class ManagerUnitListAdapter(context:Context, unitList:List<Unit>) : ArrayAdapter<Unit>(context , R.layout.unit_list_item , unitList)
{
    val unitList:List<Unit> = unitList

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var viewHolder : ViewHolder? = null
        var convertView : View? = convertView

        if(convertView == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.unit_list_item , parent , false)

            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        }
        else
        {
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.fill(unitList.get(position))
        return convertView!!
    }

    private class ViewHolder constructor(view:View)
    {
        val context : Context = view.context
        val tv_unitNumber : TextView = view.findViewById(R.id.tv_unitListItem_unitNumber)
        val tv_ownerName : TextView = view.findViewById(R.id.tv_unitListItem_ownerName)
        val tv_tag : TextView = view.findViewById(R.id.tv_unitListItem_tag)

        fun fill(unit:Unit)
        {
            tv_unitNumber.text  = context.getString(R.string.unit_number , unit.unit_number.toString())
            tv_ownerName.text   = context.getString(R.string.owner_name , unit.owner_name )
            tv_tag.text         = context.getString(R.string.unit_tag , unit.tag.toString())
        }
    }
}