package ir.sinasoheili.building_manager.VIEW

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import ir.sinasoheili.building_manager.MODEL.Notification
import ir.sinasoheili.building_manager.PRESENTER.ContractUserDashboardNotification.ContractUserDashboardNotificationView
import ir.sinasoheili.building_manager.PRESENTER.PresenterUserDashboardNotification
import ir.sinasoheili.building_manager.R

class FragmentUserDashboardNotification : Fragment(R.layout.user_dashboard_notification_fragment) , ContractUserDashboardNotificationView , View.OnClickListener
{
    private var listView : ListView? = null
    private var ivRefresh : ImageView? = null
    private var progressBar : ProgressBar? = null

    private var presenter : PresenterUserDashboardNotification? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        initObj(view)

        presenter!!.getNotificationList()
    }

    private fun initObj(view:View)
    {
        presenter = PresenterUserDashboardNotification(view.context , this)

        listView = view.findViewById(R.id.lv_userDashboard_notification_List)

        ivRefresh = view.findViewById(R.id.iv_userDashboard_notification_refresh)
        ivRefresh!!.setOnClickListener(this)

        progressBar = view.findViewById(R.id.pb_userDashboard_notification_progressBar)
    }

    override fun showNotificationList(items: List<Notification>)
    {
        visibleListView()
        invisibleProgressBar()
        invisibleRefreshButton()

        val adapter : UserNotificationListAdapter = UserNotificationListAdapter(context!! , items)
        listView!!.adapter = adapter
    }

    override fun showToast(text: String)
    {
        invisibleProgressBar()
        visibleRefreshButton()
        invisibleListView()
        Toast.makeText(context , text , Toast.LENGTH_SHORT).show()
    }

    private fun visibleListView()
    {
        listView!!.visibility = View.VISIBLE
    }

    private fun invisibleListView()
    {
        listView!!.visibility = View.GONE
    }

    override fun onClick(view: View?)
    {
        when(view)
        {
            ivRefresh ->
            {
                invisibleRefreshButton()
                visibleProgressBar()
                invisibleListView()
                presenter!!.getNotificationList()
            }
        }
    }

    private fun visibleProgressBar()
    {
        progressBar?.visibility = View.VISIBLE
    }

    private fun invisibleProgressBar()
    {
        progressBar?.visibility = View.GONE
    }

    private fun visibleRefreshButton()
    {
        ivRefresh!!.visibility = View.VISIBLE
    }

    private fun invisibleRefreshButton()
    {
        ivRefresh!!.visibility = View.GONE
    }
}