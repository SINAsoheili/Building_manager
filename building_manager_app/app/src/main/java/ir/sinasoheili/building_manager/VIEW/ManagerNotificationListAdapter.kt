package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.MODEL.Receipt
import ir.sinasoheili.building_manager.MODEL.ReceiptType
import ir.sinasoheili.building_manager.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ManagerNotificationListAdapter constructor(context:Context, val items:List<Notification>) : ArrayAdapter<Notification>(context , R.layout.manager_notification_list_item , items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View? = convertView
        var viewHolder : ViewHolder? = null

        if(view == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.manager_notification_list_item , parent , false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
        else
        {
            viewHolder = convertView?.tag as ViewHolder
        }

        viewHolder!!.fill(items.get(position))
        return view!!
    }

    class ViewHolder constructor(val view:View)
    {
        var tvTitle:TextView? = null
        var tvDate:TextView? = null

        init
        {
            tvTitle = view.findViewById(R.id.tv_managerNotificationList_title)
            tvDate = view.findViewById(R.id.tv_managerNotificationList_date)
        }

        fun fill(notification:Notification)
        {
            tvTitle!!.text = notification.title
            tvDate !!.text = Notification.convertDate(notification.date)
        }
    }
}