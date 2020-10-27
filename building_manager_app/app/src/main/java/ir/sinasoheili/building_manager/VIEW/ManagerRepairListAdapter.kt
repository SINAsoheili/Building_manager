package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.R

class ManagerRepairListAdapter constructor(context:Context, val items:List<Repair>): ArrayAdapter<Repair>(context , R.layout.manager_repair_list_item , items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View? = convertView
        var viewHolder : ViewHolder? = null

        if(view == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.manager_repair_list_item , parent , false)
            viewHolder = ViewHolder(view)
            view.tag =  viewHolder
        }
        else
        {
            viewHolder = view.tag as ViewHolder
        }

        viewHolder?.fill(items.get(position))
        return view!!
    }

    class ViewHolder constructor(val view:View)
    {
        private var tvTitle:TextView? = null
        private var tvDate :TextView? = null

        init
        {
            tvTitle = view.findViewById(R.id.tv_ManagerRepairList_title)
            tvDate = view.findViewById(R.id.tv_ManagerRepairList_date)
        }

        fun fill(repair:Repair)
        {
            tvTitle?.text = repair.title
            tvDate?.text = Repair.convertDate(repair.date)
        }
    }
}