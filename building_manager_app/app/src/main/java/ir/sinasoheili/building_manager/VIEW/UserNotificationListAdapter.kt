package ir.sinasoheili.building_manager.VIEW

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.R

class UserNotificationListAdapter constructor(context:Context, val items:List<Notification>) : ArrayAdapter<Notification>(context , R.layout.user_notification_list_item , items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
    {
        var view : View? = convertView
        var viewHolder : ViewHolder? = null

        if(view == null)
        {
            val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.user_notification_list_item , parent , false)
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
        var tvText:TextView? = null
        var tvDate:TextView? = null

        init
        {
            tvTitle = view.findViewById(R.id.tv_userNotificationList_title)
            tvText = view.findViewById(R.id.tv_userNotificationList_text)
            tvDate = view.findViewById(R.id.tv_userNotificationList_date)
        }

        fun fill(notification:Notification)
        {
            tvTitle?.text = notification.title
            tvText?.text = notification.text
            tvDate?.text = Notification.convertDate(notification.date!!)
        }
    }
}