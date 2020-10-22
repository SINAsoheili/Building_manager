package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardNotification.ContractUserDashboardNotificationView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardNotification
import ir.sinasoheili.building_manager.R

class FragmentUserDashboardNotification : Fragment(R.layout.user_dashboard_notification_fragment) , ContractUserDashboardNotificationView , View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null

    private var presenter : PresenterUserDashboardNotification? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenter!!.getNotificationList()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterUserDashboardNotification(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_repair_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_repair_refresh)
        ivRefresh!!.setOnClickListener(this)
    }

    override fun showRefreshButton()
    {
        visibleRefreshButton()
    }

    override fun showNotificationList(items: List<Notification>)
    {
        visibleListView()

        val adapter:ArrayAdapter<Notification> = ArrayAdapter(context!! , android.R.layout.simple_list_item_1 , items)
        listView!!.adapter = adapter
    }

    private fun visibleListView()
    {
        listView!!.visibility = View.VISIBLE
        ivRefresh!!.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        listView!!.visibility = View.GONE
        ivRefresh!!.visibility = View.VISIBLE
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivRefresh ->
            {
                presenter!!.getNotificationList()
            }
        }
    }
}