package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Repair
import ir.sinasoheili.building_manager.R

class UserRepairListAdapter constructor(context:Context, val items:List<Repair>): ArrayAdapter<Repair>(context , R.layout.user_repair_list_item , items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View? = convertView
        var viewHolder : ViewHolder? = null

        if(view == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.user_repair_list_item , parent , false)
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
        private var tvComment:TextView? = null
        private var tvAmount :TextView? = null

        init
        {
            tvTitle = view.findViewById(R.id.tv_UserRepairList_title)
            tvDate = view.findViewById(R.id.tv_UserRepairList_date)
            tvComment = view.findViewById(R.id.tv_UserRepairList_comment)
            tvAmount = view.findViewById(R.id.tv_UserRepairList_amount)
        }

        fun fill(repair:Repair)
        {
            tvTitle?.text = repair.title
            tvDate?.text = Repair.convertDate(repair.date)
            tvComment?.text = repair.comment
            tvAmount?.text = repair.amount.toString()
        }
    }
}